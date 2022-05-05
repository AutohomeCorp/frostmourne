package com.autohome.frostmourne.monitor.tool;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * LocalDataTime util
 *
 * @author Aping
 * @since 2022/05/03 15:02
 */
public class LocalDateTimeUtils {

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    public static final String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，仅支持yyyy-MM-dd'T'HH:mm:ss格式，例如：2007-12-03T10:15:30<br>
     * 即{@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}
     *
     * @param text 日期时间字符串
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text) {
        return parse(text, (DateTimeFormatter)null);
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，格式支持日期时间、日期、时间<br>
     * 如果formatter为{code null}，则使用{@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}
     *
     * @param text 日期时间字符串
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        if (null == text) {
            return null;
        }
        if (null == formatter) {
            return LocalDateTime.parse(text);
        }

        return of(formatter.parse(text));
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}
     *
     * @param text 日期时间字符串
     * @param format 日期格式，类似于yyyy-MM-dd HH:mm:ss,SSS
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text, String format) {
        if (null == text) {
            return null;
        }

        DateTimeFormatter formatter = null;
        if (StringUtils.isNotBlank(format)) {
            // 修复yyyyMMddHHmmssSSS格式不能解析的问题
            // fix issue#1082
            // see https://stackoverflow.com/questions/22588051/is-java-time-failing-to-parse-fraction-of-second
            // jdk8 bug at: https://bugs.openjdk.java.net/browse/JDK-8031085
            if (!PURE_DATETIME_PATTERN.equals(format)
                && format.toLowerCase().startsWith(PURE_DATETIME_PATTERN.toLowerCase())) {
                final String fraction = format.substring(PURE_DATETIME_PATTERN.length());

                if (Pattern.compile("[S]{1,2}").matcher(fraction).matches()) {
                    // 将yyyyMMddHHmmssS、yyyyMMddHHmmssSS的日期统一替换为yyyyMMddHHmmssSSS格式，用0补
                    for (int i = 0; i < 3 - fraction.length(); i++) {
                        text += "0";
                    }
                }
                formatter = new DateTimeFormatterBuilder().appendPattern(PURE_DATETIME_PATTERN)
                    .appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
            } else {
                formatter = DateTimeFormatter.ofPattern(format);
            }
        }

        return parse(text, formatter);
    }

    /**
     * 格式化日期时间为yyyy-MM-dd HH:mm:ss格式
     *
     * @param time {@link LocalDateTime}
     * @return 格式化后的字符串
     */
    public static String formatNormal(LocalDateTime time) {
        return format(time, NORM_DATETIME_PATTERN);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param time {@link LocalDateTime}
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        return formatter.format(time);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param time {@link LocalDateTime}
     * @param format 日期格式，类似于yyyy-MM-dd HH:mm:ss,SSS
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime time, String format) {
        if (null == time) {
            return null;
        }
        return format(time, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 格式化日期时间为yyyy-MM-dd格式
     *
     * @param date {@link LocalDate}
     * @return 格式化后的字符串
     */
    public static String formatNormal(LocalDate date) {
        return format(date, NORM_DATETIME_PATTERN);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param date {@link LocalDate}
     * @param formatter 日期格式化器
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return formatter.format(date);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param date {@link LocalDate}
     * @param format 日期格式，类似于yyyy-MM-dd
     * @return 格式化后的字符串
     * @since 5.3.10
     */
    public static String format(LocalDate date, String format) {
        if (null == date) {
            return null;
        }
        return format(date, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 日期偏移,根据field不同加不同值（偏移会修改传入的对象）
     *
     * @param time {@link LocalDateTime}
     * @param number 偏移量，正数为向后偏移，负数为向前偏移
     * @param field 偏移单位，见{@link ChronoUnit}，不能为null
     * @return 偏移后的日期时间
     */
    public static LocalDateTime offset(LocalDateTime time, long number, TemporalUnit field) {
        if (null == time) {
            return null;
        }

        return time.plus(number, field);
    }

    /**
     * 获取两个日期的表象时间差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 比如2011年2月1日，和2021年8月11日，日相差了10天，月相差6月
     *
     * @param startTimeInclude 开始时间（包括）
     * @param endTimeExclude 结束时间（不包括）
     * @return 时间差
     * @since 5.4.5
     */
    public static Period betweenPeriod(LocalDate startTimeInclude, LocalDate endTimeExclude) {
        return Period.between(startTimeInclude, endTimeExclude);
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为{@link Duration}对象，通过调用toXXX方法返回相差单位
     *
     * @param startTimeInclude 开始时间（包含）
     * @param endTimeExclude 结束时间（不包含）
     * @return 时间差 {@link Duration}对象
     */
    public static Duration between(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude) {
        return Duration.between(startTimeInclude, endTimeExclude);
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为时间差的long值
     *
     * @param startTimeInclude 开始时间（包括）
     * @param endTimeExclude 结束时间（不包括）
     * @param unit 时间差单位
     * @return 时间差
     */
    public static long between(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude, ChronoUnit unit) {
        return unit.between(startTimeInclude, endTimeExclude);
    }

    /**
     * 将 {@link TimeUnit} 转换为 {@link ChronoUnit}.
     *
     * @param unit 被转换的{@link TimeUnit}单位，如果为{@code null}返回{@code null}
     * @return {@link ChronoUnit}
     */
    public static ChronoUnit toChronoUnit(TimeUnit unit) throws IllegalArgumentException {
        if (null == unit) {
            return null;
        }
        switch (unit) {
            case NANOSECONDS:
                return ChronoUnit.NANOS;
            case MICROSECONDS:
                return ChronoUnit.MICROS;
            case MILLISECONDS:
                return ChronoUnit.MILLIS;
            case SECONDS:
                return ChronoUnit.SECONDS;
            case MINUTES:
                return ChronoUnit.MINUTES;
            case HOURS:
                return ChronoUnit.HOURS;
            case DAYS:
                return ChronoUnit.DAYS;
            default:
                throw new IllegalArgumentException("Unknown TimeUnit constant");
        }
    }

    /**
     * 转换 {@link ChronoUnit} 到 {@link TimeUnit}.
     *
     * @param unit {@link ChronoUnit}，如果为{@code null}返回{@code null}
     * @return {@link TimeUnit}
     * @throws IllegalArgumentException 如果{@link TimeUnit}没有对应单位抛出
     */
    public static TimeUnit toTimeUnit(ChronoUnit unit) throws IllegalArgumentException {
        if (null == unit) {
            return null;
        }
        switch (unit) {
            case NANOS:
                return TimeUnit.NANOSECONDS;
            case MICROS:
                return TimeUnit.MICROSECONDS;
            case MILLIS:
                return TimeUnit.MILLISECONDS;
            case SECONDS:
                return TimeUnit.SECONDS;
            case MINUTES:
                return TimeUnit.MINUTES;
            case HOURS:
                return TimeUnit.HOURS;
            case DAYS:
                return TimeUnit.DAYS;
            default:
                throw new IllegalArgumentException("ChronoUnit cannot be converted to TimeUnit: " + unit);
        }
    }

    /**
     * 修改为一天的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @param time 日期时间
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDay(LocalDateTime time) {
        return time.with(LocalTime.MIN);
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDay(LocalDateTime time) {
        return time.with(LocalTime.MAX);
    }

    /**
     * 是否为周末（周六或周日）
     *
     * @param localDateTime 判定的日期{@link LocalDateTime}
     * @return 是否为周末（周六或周日）
     */
    public static boolean isWeekend(LocalDateTime localDateTime) {
        return isWeekend(localDateTime.toLocalDate());
    }

    /**
     * 是否为周末（周六或周日）
     *
     * @param localDate 判定的日期{@link LocalDate}
     * @return 是否为周末（周六或周日）
     */
    public static boolean isWeekend(LocalDate localDate) {
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return DayOfWeek.SATURDAY == dayOfWeek || DayOfWeek.SUNDAY == dayOfWeek;
    }

    /**
     * {@link Instant}转{@link LocalDateTime}，使用默认时区
     *
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant) {
        return of(instant, ZoneId.systemDefault());
    }

    /**
     * {@link Instant}转{@link LocalDateTime}，使用UTC时区
     *
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ofUTC(Instant instant) {
        return of(instant, ZoneId.of("UTC"));
    }

    /**
     * {@link ZonedDateTime}转{@link LocalDateTime}
     *
     * @param zonedDateTime {@link ZonedDateTime}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(ZonedDateTime zonedDateTime) {
        if (null == zonedDateTime) {
            return null;
        }
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * {@link Instant}转{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param zoneId 时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, ZoneId zoneId) {
        if (null == instant) {
            return null;
        }

        return LocalDateTime.ofInstant(instant, zoneId != null ? zoneId : ZoneId.systemDefault());
    }

    /**
     * {@link Instant}转{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param timeZone 时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, TimeZone timeZone) {
        if (null == instant) {
            return null;
        }

        return of(instant, timeZone != null ? timeZone.toZoneId() : TimeZone.getDefault().toZoneId());
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用默认时区
     *
     * <p>
     * 注意：此方法使用默认时区，如果非UTC，会产生时间偏移
     * </p>
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用UTC时区
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ofUTC(long epochMilli) {
        return ofUTC(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * 毫秒转{@link LocalDateTime}，根据时区不同，结果会产生时间偏移
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param zoneId 时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, ZoneId zoneId) {
        return of(Instant.ofEpochMilli(epochMilli), zoneId);
    }

    /**
     * 毫秒转{@link LocalDateTime}，结果会产生时间偏移
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param timeZone 时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, TimeZone timeZone) {
        return of(Instant.ofEpochMilli(epochMilli), timeZone);
    }

    /**
     * {@link TemporalAccessor}转{@link LocalDateTime}，使用默认时区
     *
     * @param temporalAccessor {@link TemporalAccessor}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }

        if (temporalAccessor instanceof LocalDate) {
            return ((LocalDate)temporalAccessor).atStartOfDay();
        }

        return LocalDateTime.of(temporalAccessorGet(temporalAccessor, ChronoField.YEAR),
            temporalAccessorGet(temporalAccessor, ChronoField.MONTH_OF_YEAR),
            temporalAccessorGet(temporalAccessor, ChronoField.DAY_OF_MONTH),
            temporalAccessorGet(temporalAccessor, ChronoField.HOUR_OF_DAY),
            temporalAccessorGet(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
            temporalAccessorGet(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
            temporalAccessorGet(temporalAccessor, ChronoField.NANO_OF_SECOND));
    }

    /**
     * {@link TemporalAccessor}转{@link LocalDate}，使用默认时区
     *
     * @param temporalAccessor {@link TemporalAccessor}
     * @return {@link LocalDate}
     * @since 5.3.10
     */
    public static LocalDate ofDate(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }

        if (temporalAccessor instanceof LocalDateTime) {
            return ((LocalDateTime)temporalAccessor).toLocalDate();
        }

        return LocalDate.of(temporalAccessorGet(temporalAccessor, ChronoField.YEAR),
            temporalAccessorGet(temporalAccessor, ChronoField.MONTH_OF_YEAR),
            temporalAccessorGet(temporalAccessor, ChronoField.DAY_OF_MONTH));
    }

    /**
     * 安全获取时间的某个属性，属性不存在返回0
     *
     * @param temporalAccessor 需要获取的时间对象
     * @param field 需要获取的属性
     * @return 时间的值，如果无法获取则默认为 0
     */
    public static int temporalAccessorGet(TemporalAccessor temporalAccessor, TemporalField field) {
        if (temporalAccessor.isSupported(field)) {
            return temporalAccessor.get(field);
        }

        return (int)field.range().getMinimum();
    }

}
