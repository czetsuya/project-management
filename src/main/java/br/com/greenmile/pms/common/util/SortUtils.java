package br.com.greenmile.pms.common.util;

import org.springframework.data.domain.Sort;

public abstract class SortUtils {

    private SortUtils() {
    }

    public static Sort getSort(Sort.Direction direction, String... properties) {
        return new Sort(direction, properties);
    }
}
