
package com.skypro.courswork3.courswork3.controller;

import com.skypro.courswork3.courswork3.controller.dto.ResponceDto;
import com.skypro.courswork3.courswork3.model.socks.Color;
import com.skypro.courswork3.courswork3.model.socks.Size;
import com.skypro.courswork3.courswork3.model.socks.Socks;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import com.skypro.courswork3.courswork3.service.StocksStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета носков", description = "Регистрация прихода/ отпуска со склада/ списание брака" +
        "/ подсчет количества")
@RequiredArgsConstructor
public class SocksStoreController {

    private final StocksStoreService stocksStoreService;

    @PostMapping
    @Operation(
            summary = "регистрация прихода товара со склада",
            description = "регистрация товара со склада",
            responses =
            {
            @ApiResponse(
                    responseCode = "200",
                    description = "операция успешна",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отутствуют или имеют некорректный формат"
            ),
            @ApiResponse(
                     responseCode = "500",
                     description = "внутрения ошибка приложения"
            )
    }
    )

    public ResponseEntity<ResponceDto> accept(@RequestBody SocksBatch socksBatch) {
        stocksStoreService.accept(socksBatch);
        return ResponseEntity.ok(new ResponceDto("Носки успешно добавлены"));
    }

    @PutMapping
    @Operation(
            summary = "регистрация отпуска носков со склада",
            description = "регистрация отпуска со склада"
    )
    @ApiResponse(
            responseCode = "200",
            description = "операция успешна"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "внутрения ошибка приложения"
    )
    public ResponseEntity<ResponceDto> issuance(@RequestBody SocksBatch socksBatch) {
        int countSocks = stocksStoreService.issuance(socksBatch);
        return ResponseEntity.ok(new ResponceDto(countSocks + "Столько носков было отпущено со склада"));
    }

    @GetMapping
    @Operation(
            summary = "Возращение носков на склад",
            description = "Соответвующих переданному параметру критериев запроса"
    )
    @ApiResponse(
            responseCode = "200",
            description = "операция успешна"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "внутрения ошибка приложения"
    )
    public ResponseEntity<ResponceDto> getCount(@RequestParam Color color,
                                         @RequestParam Size size,
                                         @RequestParam int cottonMax,
                                         @RequestParam int cottonMin) {
        int countSocks = stocksStoreService.getCount(color, size, cottonMax, cottonMin);
        return ResponseEntity.ok(new ResponceDto("Количество носков " + countSocks));
    }
    @DeleteMapping
    @Operation(
            summary = "Регистрация списанного товара",
            description = "Соответвующих переданному параметру критериев запроса"
    )
    @ApiResponse(
            responseCode = "200",
            description = "операция успешна"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "внутрения ошибка приложения"
    )
    public ResponseEntity<ResponceDto> reject(@RequestParam Color color,
                                         @RequestParam Size size,
                                         @RequestParam int cottonMax,
                                         @RequestParam int cottonMin) {
        int countSocks = stocksStoreService.getCount(color, size, cottonMax, cottonMin);
        return ResponseEntity.ok(new ResponceDto("Количество носков которые списаны: " + countSocks));
    }
}
