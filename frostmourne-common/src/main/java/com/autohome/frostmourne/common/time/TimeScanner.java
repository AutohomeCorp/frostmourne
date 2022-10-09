package com.autohome.frostmourne.common.time;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 16:46
 */
public class TimeScanner {

    private String dateString;

    private int pos, pos_save;
    private TimeToken token, token_save;

    static final TimeToken[] WORDS = {
        new TimeToken("now", TimeToken.NOW), new TimeToken("n", TimeToken.NOW), new TimeToken("start", TimeToken.START), new TimeToken("s", TimeToken.START),
    };

    static TimeToken[] MULTIPLIERS = {new TimeToken("second", TimeToken.SECONDS),
        new TimeToken("seconds", TimeToken.SECONDS),
        new TimeToken("sec", TimeToken.SECONDS),
        new TimeToken("s", TimeToken.SECONDS),
        new TimeToken("minute", TimeToken.MINUTES),
        new TimeToken("minutes", TimeToken.MINUTES),
        new TimeToken("min", TimeToken.MINUTES),
        new TimeToken("m", TimeToken.MINUTES),
        new TimeToken("hour", TimeToken.HOURS),
        new TimeToken("hours", TimeToken.HOURS),
        new TimeToken("hr", TimeToken.HOURS),
        new TimeToken("h", TimeToken.HOURS),
        new TimeToken("day", TimeToken.DAYS),
        new TimeToken("days", TimeToken.DAYS),
        new TimeToken("d", TimeToken.DAYS),
        new TimeToken("week", TimeToken.WEEKS),
        new TimeToken("weeks", TimeToken.WEEKS),
        new TimeToken("wk", TimeToken.WEEKS),
        new TimeToken("w", TimeToken.WEEKS),
        new TimeToken("Month", TimeToken.MONTHS),
        new TimeToken("Months", TimeToken.MONTHS),
        new TimeToken("Mon", TimeToken.MONTHS),
        new TimeToken("year", TimeToken.YEARS),
        new TimeToken("years", TimeToken.YEARS),
        new TimeToken("yr", TimeToken.YEARS),
        new TimeToken("y", TimeToken.YEARS),
        new TimeToken(null, 0)
    };

    TimeToken[] specials = WORDS;

    public TimeScanner(String dateString) {
        this.dateString = dateString;
    }

    void setContext(boolean parsingWords) {
        specials = parsingWords ? WORDS : MULTIPLIERS;
    }

    TimeToken nextToken() {
        StringBuffer buffer = new StringBuffer("");
        while (pos < dateString.length()) {
            char c = dateString.charAt(pos++);
            if (Character.isWhitespace(c) || c == '_' || c == ',') {
                continue;
            }
            buffer.append(c);
            if (Character.isDigit(c)) {
                // pick as many digits as possible
                while (pos < dateString.length()) {
                    char next = dateString.charAt(pos);
                    if (Character.isDigit(next)) {
                        buffer.append(next);
                        pos++;
                    } else {
                        break;
                    }
                }
                String value = buffer.toString();
                return token = new TimeToken(value, TimeToken.NUMBER);
            }
            if (Character.isLetter(c)) {
                // pick as many letters as possible
                while (pos < dateString.length()) {
                    char next = dateString.charAt(pos);
                    if (Character.isLetter(next)) {
                        buffer.append(next);
                        pos++;
                    } else {
                        break;
                    }
                }
                String value = buffer.toString();
                return token = new TimeToken(value, parseToken(value));
            }
            switch (c) {
                case '+':
                    return token = new TimeToken("+", TimeToken.PLUS);
                case '-':
                    return token = new TimeToken("-", TimeToken.MINUS);
                default:
                    pos--;
                    return token = new TimeToken(null, TimeToken.EOF);
            }
        }
        return token = new TimeToken(null, TimeToken.EOF);
    }

    void saveState() {
        token_save = token;
        pos_save = pos;
    }

    TimeToken restoreState() {
        pos = pos_save;
        return token = token_save;
    }

    private int parseToken(String arg) {
        for (int i = 0; specials[i].value != null; i++) {
            if (specials[i].value.equalsIgnoreCase(arg)) {
                return specials[i].id;
            }
        }
        return TimeToken.ID;
    }
}
