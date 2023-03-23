package com.skypro.courswork3.courswork3.service.impl;

import com.skypro.courswork3.courswork3.model.socks.Color;
import com.skypro.courswork3.courswork3.model.socks.Size;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import com.skypro.courswork3.courswork3.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validation(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null &&
                socksBatch.getQuantity() > 0 &&
                socksBatch.getSocks().getColor() != null &&
                socksBatch.getSocks().getSize() != null;
    }

    @Override
    public boolean validation(Color color, Size size, int cottonMax, int cottonMin) {
        return color != null && size != null && checkCotton(cottonMax, cottonMin);
    }

    private boolean checkCotton(int cottonMax, int cottonMin) {
        return cottonMin >= 0 && cottonMax <= 100;
    }
}
