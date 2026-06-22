package com.frro.bus.ticket.common.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class DateTimeUtils {
    public static String formatDateTime(ZonedDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_INSTANT);
    }
}
