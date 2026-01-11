package com.supermercado.ventas.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {
    public static BigDecimal IVA = new BigDecimal("0.15");
    public static RoundingMode ROUNDING_MODE_FOR_VENTAS = RoundingMode.HALF_EVEN;
    public static int SCALE_FOR_VENTAS = 4;
}
