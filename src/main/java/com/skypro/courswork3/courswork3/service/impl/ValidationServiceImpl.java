package com.skypro.courswork3.courswork3.service.impl;

import com.skypro.courswork3.courswork3.exception.ValidationException;
import com.skypro.courswork3.courswork3.model.socks.Color;
import com.skypro.courswork3.courswork3.model.socks.Size;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import com.skypro.courswork3.courswork3.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validation(SocksBatch socksBatch) {
        if (socksBatch.getSocks() == null
                || socksBatch.getQuantity() <= 0
                || socksBatch.getSocks().getColor() == null
                || socksBatch.getSocks().getSize() == null
                || socksBatch.getSocks().getCottonPart() <= 0
                || socksBatch.getSocks().getCottonPart() > 100)
        {
            throw new ValidationException("значение пустое");
        } else {
            return (socksBatch.getSocks() != null
                    && socksBatch.getQuantity() > 0
                    && socksBatch.getSocks().getColor() != null
                    && socksBatch.getSocks().getSize() != null
                    && checkCotton(socksBatch.getSocks().getCottonPart(),
                    socksBatch.getSocks().getCottonPart()));
        }
    }


    @Override
    public boolean validation(Color color, Size size, int cottonMax, int cottonMin) {
        return color != null
                && size != null
                && checkCotton(cottonMax, cottonMin);
    }

    private boolean checkCotton(int cottonMax, int cottonMin) {
        return cottonMin >= 0 && cottonMax <= 100;
    }
}
