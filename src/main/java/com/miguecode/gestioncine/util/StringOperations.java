package com.miguecode.gestioncine.util;

public class StringOperations {
    public static String getNumberFormatted(int number, int maxNumber) {
        int length = String.valueOf(maxNumber).length();
        return String.format("%0" + length + "d", number);
    }

}
