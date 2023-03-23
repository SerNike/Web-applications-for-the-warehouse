package com.skypro.courswork3.courswork3.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.courswork3.courswork3.exception.OperationType;
import com.skypro.courswork3.courswork3.model.operation.StoreOperation;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import com.skypro.courswork3.courswork3.service.FileService;
import com.skypro.courswork3.courswork3.service.StoreOperionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StoryOperationSocksImpl implements StoreOperionService {

    private List<StoreOperation> operations = new ArrayList<>();
    private final FileService fileService;
    private Path patch = Path.of("src/main/resources", "socks-operation.json");
    @Override
    public void accept(SocksBatch socksBatch) {
        operations.add(new StoreOperation(OperationType.ACCEPT, socksBatch));
    }

    @Override
    public void issuance(SocksBatch socksBatch) {
        operations.add(new StoreOperation(OperationType.ISSUANSE, socksBatch));
    }

    @Override
    public void reject(SocksBatch socksBatch) {
        operations.add(new StoreOperation(OperationType.REJECT, socksBatch));
    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(operations, patch).toFile();
    }

    @Override
    public void importFromFile(MultipartFile file) throws IOException {
        operations =
                fileService.uploadFromFile(file, patch, new TypeReference<List<StoreOperation>>() {
                });
    }
}
