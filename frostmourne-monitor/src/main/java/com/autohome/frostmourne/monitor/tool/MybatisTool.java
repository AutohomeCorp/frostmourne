package com.autohome.frostmourne.monitor.tool;

import org.elasticsearch.common.Strings;

public class MybatisTool {

    public static boolean notNull(Object value) {
        return value != null;
    }

    public static boolean notNullAndEmpty(String value) {
        return !Strings.isNullOrEmpty(value);
    }

    public static boolean notNullAndZero(Long value) {
        return value != null && value > 0;
    }

    public static boolean notNullAndZero(Integer value) {
        return value != null && value > 0;
    }

    public static String twoSideVagueMatch(String value) {
        return "%" + value + "%";
    }

    public static String leftVagueMatch(String value) {
        return "%" + value;
    }

    public static String rightVagueMatch(String value) {
        return value + "%";
    }
}
