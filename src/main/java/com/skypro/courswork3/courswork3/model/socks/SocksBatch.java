package com.skypro.courswork3.courswork3.model.socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * партия носков
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksBatch {
    private Socks socks;
    private int quantity;


}
