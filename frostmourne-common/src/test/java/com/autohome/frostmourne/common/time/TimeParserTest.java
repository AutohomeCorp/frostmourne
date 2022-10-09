package com.autohome.frostmourne.common.time;

import static org.junit.jupiter.api.Assertions.*;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 17:29
 */
class TimeParserTest {

    @Test
    void parseTest_with_d() {
        TimeParser timeParser = new TimeParser("now-1d");
        TimeSpec timeSpec = timeParser.parse();
        long time = timeSpec.getTimestamp();

        DateTime dateTime = new DateTime(time * 1000L);
        System.out.println(dateTime);
    }

    @Test
    void parseTest_with_m() {
        TimeParser timeParser = new TimeParser("now - 5m");
        TimeSpec timeSpec = timeParser.parse();
        long time = timeSpec.getTimestamp();

        DateTime dateTime = new DateTime(time * 1000L);
        System.out.println(dateTime);
    }
}