package com.skypro.courswork3.courswork3.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
@Service
public interface FileService {
    <T> Path saveToFile(T content, Path path) throws IOException;

    <T> List<T> uploadFromFile(MultipartFile file, Path path, TypeReference<List<T>> typeReference) throws IOException;

    }
