
package com.skypro.courswork3.courswork3.model.socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *  класс носков
 *  цвет, размер, содержание хлопка
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;
}

