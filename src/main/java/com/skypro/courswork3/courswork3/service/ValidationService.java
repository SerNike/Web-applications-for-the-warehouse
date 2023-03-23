package com.skypro.courswork3.courswork3.service;

import com.skypro.courswork3.courswork3.model.socks.Color;
import com.skypro.courswork3.courswork3.model.socks.Size;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;

public interface ValidationService {
    boolean validation(SocksBatch socksBatch);

    boolean validation(Color color, Size size, int cottonMax, int cottonMin);
}
