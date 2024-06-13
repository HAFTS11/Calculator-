package com.houarizegai.calculator.util;

import java.awt.*;
import java.util.Optional;

public class ColorUtil {

    // Private constructor to prevent instantiation of this utility class
    private ColorUtil() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Converts a hexadecimal color string to a Color object.
     *
     * @param colorHex the hexadecimal color string (e.g., "FFAABB").
     * @return a Color object corresponding to the given hexadecimal string, or null if the string is null.
     */
    public static Color hex2Color(String colorHex) {
        return Optional.ofNullable(colorHex)
                .map(hex -> new Color(
                        Integer.valueOf(colorHex.substring(0, 2), 16),  // Red component
                        Integer.valueOf(colorHex.substring(2, 4), 16),  // Green component
                        Integer.valueOf(colorHex.substring(4, 6), 16))) // Blue component
                .orElse(null); // Return null if the input string is null
    }
}
