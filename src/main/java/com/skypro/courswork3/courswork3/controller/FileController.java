package com.skypro.courswork3.courswork3.controller;

import com.skypro.courswork3.courswork3.service.StocksStoreService;
import com.skypro.courswork3.courswork3.service.StoreOperionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks/files")
@Tag(name = "API для работы с файлами", description = "Импорт или экспорт файла")
@RequiredArgsConstructor
public class FileController {

    private final StocksStoreService stocksStoreService;
    private final StoreOperionService service;

    @GetMapping("/export")
    @Operation(
            summary = "выгрузка json-файла носков",
            description = "выгрузка файла"
    )
    public ResponseEntity<InputStreamResource> downloadSocksFile() {
        try {
            File socksFile = stocksStoreService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + socksFile.getName())
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/import")
    @Operation(
            summary = "загрузка json-файла носков",
            description = "загрузка файла"
    )
    public ResponseEntity<String> uploadSocksFile (@RequestParam MultipartFile file) {
        try {
            stocksStoreService.importFromFile(file);
            return ResponseEntity.ok("Файл загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка при загрузки файла");
        }
    }
        @GetMapping("/operation/export")
        @Operation(
                summary = "выгрузка json-файла операций с носками",
                description = "выгрузка файла операций с носками"
        )
        public ResponseEntity<InputStreamResource> downloadOperationFile() {
            try {
                File socksFile = service.exportFile();
                InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentLength(socksFile.length())
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=" + socksFile.getName())
                        .body(resource);

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }
        @GetMapping("/operation/import")
        @Operation(
                summary = "загрузка json-файла операций с носками",
                description = "загрузка файла операций с носками"
        )
        public ResponseEntity<String> uploadOperationFile (@RequestParam MultipartFile file) {
            try {
                service.importFromFile(file);
                return ResponseEntity.ok("Файл загружен");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Ошибка при загрузки файла");
            }
    }
}