package com.frro.bus.ticket.common.utils;

public abstract class StringSearchUtils {
    public static String likePattern(String value) {
        return "%" + value.toLowerCase() + "%";
    }
}
