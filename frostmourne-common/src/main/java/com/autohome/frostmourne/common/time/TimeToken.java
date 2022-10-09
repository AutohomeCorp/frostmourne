package com.autohome.frostmourne.common.time;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 16:46
 */
public class TimeToken {

    public static final int NOW = 1;
    public static final int SECONDS = 2;
    public static final int MINUTES = 3;
    public static final int HOURS = 4;
    public static final int DAYS = 5;
    public static final int WEEKS = 6;
    public static final int MONTHS = 7;
    public static final int YEARS = 8;
    public static final int NUMBER = 9;
    public static final int PLUS = 10;
    public static final int MINUS = 11;
    public static final int START = 12;
    public static final int END = 13;
    public static final int ID = 26;
    public static final int EOF = -1;

    /**
     * token name
     */
    final String value;
    /**
     * token id
     */
    final int id;

    public TimeToken(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public String toString() {
        return value + " [" + id + "]";
    }
}
