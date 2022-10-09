package com.autohome.frostmourne.common.time;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 18:06
 */
public class TimeParserUtil {

    public static long parse(String str) {
        TimeParser timeParser = new TimeParser(str);
        TimeSpec timeSpec = timeParser.parse();
        return timeSpec.getTimestamp();
    }

    public static long parse(String str, long nowInSeconds) {
        TimeParser timeParser = new TimeParser(str);
        TimeSpec timeSpec = timeParser.parse(nowInSeconds);
        return timeSpec.getTimestamp();
    }
}
