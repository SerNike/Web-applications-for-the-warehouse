
package com.skypro.courswork3.courswork3.service;

import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public interface StoreOperionService {
    void accept(SocksBatch socksBatch);

    void issuance(SocksBatch socksBatch);

    void reject(SocksBatch socksBatch);
    File exportFile() throws IOException;

    void importFromFile(MultipartFile file) throws IOException;
}
