package com.frro.bus.ticket.common.utils;

import org.springframework.data.domain.ExampleMatcher;

public class SearchServiceUtils {
    public static final ExampleMatcher DEFAULT_MATCHER = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    private SearchServiceUtils() {
        throw new UnsupportedOperationException("Utility class");
    }
}
