package com.skypro.courswork3.courswork3.service;

import com.skypro.courswork3.courswork3.model.socks.Color;
import com.skypro.courswork3.courswork3.model.socks.Size;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface StocksStoreService {
    void accept(SocksBatch socksBatch);
    int issuance(SocksBatch socksBatch);
    int reject(SocksBatch socksBatch);
    int getCount(Color color, Size size, int cottonMax, int cottonMin);

    File exportFile() throws IOException;

    void importFromFile(MultipartFile file) throws IOException;
}
