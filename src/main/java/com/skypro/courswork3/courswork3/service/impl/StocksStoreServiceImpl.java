package com.skypro.courswork3.courswork3.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.courswork3.courswork3.exception.ValidationException;
import com.skypro.courswork3.courswork3.model.operation.StoreOperation;
import com.skypro.courswork3.courswork3.model.socks.Color;
import com.skypro.courswork3.courswork3.model.socks.Size;
import com.skypro.courswork3.courswork3.model.socks.Socks;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import com.skypro.courswork3.courswork3.repository.StocksRepository;
import com.skypro.courswork3.courswork3.service.FileService;
import com.skypro.courswork3.courswork3.service.StocksStoreService;
import com.skypro.courswork3.courswork3.service.StoreOperionService;
import com.skypro.courswork3.courswork3.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StocksStoreServiceImpl implements StocksStoreService {

    private final StocksRepository stocksRepository;
    private final ValidationService validationService;
    private final FileService fileService;
    private Path patch = Path.of("src/main/resources", "socks.json");
    private final StoreOperionService operionService;


    @Override

    public void accept(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        operionService.accept(socksBatch);
        stocksRepository.save(socksBatch);
    }

    @Override
    public int issuance(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        operionService.issuance(socksBatch);
        return stocksRepository.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        operionService.reject(socksBatch);
        return stocksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMax, int cottonMin) {
        if (validationService.validation(color, size, cottonMax, cottonMin)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksMap = stocksRepository.getAll();
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();
            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(stocksRepository.getList(), patch).toFile();
    }

    @Override
    public void importFromFile(MultipartFile file) throws IOException {
        List<SocksBatch> socksBatches =
                fileService.uploadFromFile(file, patch, new TypeReference<List<SocksBatch>>() {
        });

        stocksRepository.replace(socksBatches);
    }

    private void checkSocksBatch(SocksBatch socksBatch) {
        if (validationService.validation(socksBatch)) {
            throw new ValidationException();
        }
    }
}
