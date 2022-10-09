package com.autohome.frostmourne.common.time;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 16:47
 */
public class TimeParser {

    private static final int PREVIOUS_OP = -1;

    TimeToken token;
    TimeScanner scanner;
    TimeSpec spec;

    int op = TimeToken.PLUS;
    int prevMultiplier = -1;

    public TimeParser(String dateString) {
        scanner = new TimeScanner(dateString);
        spec = new TimeSpec(dateString);
    }

    private void expectToken(int desired, String errorMessage) {
        token = scanner.nextToken();
        if (token.id != desired) {
            throw new RuntimeException(errorMessage);
        }
    }

    private void plusMinus(int doop) {
        if (doop >= 0) {
            op = doop;
            expectToken(TimeToken.NUMBER, "There should be number after " +
                    (op == TimeToken.PLUS ? '+' : '-'));
            //reset months-minutes guessing mechanics
            prevMultiplier = -1;
        }
        int delta = Integer.parseInt(token.value);
        token = scanner.nextToken();
        prevMultiplier = token.id;
        delta *= (op == TimeToken.PLUS) ? +1 : -1;
        switch (token.id) {
            case TimeToken.YEARS:
                spec.dyear += delta;
                break;
            case TimeToken.MONTHS:
                spec.dmonth += delta;
                break;
            case TimeToken.WEEKS:
                delta *= 7;
                spec.dday += delta;
                break;
            case TimeToken.DAYS:
                spec.dday += delta;
                break;
            case TimeToken.HOURS:
                spec.dhour += delta;
                break;
            case TimeToken.MINUTES:
                spec.dmin += delta;
                break;
            case TimeToken.SECONDS:
            default: // default is 'seconds'
                spec.dsec += delta;
                break;
        }
    }

    private void timeOfDay() {
        int hour, minute = 0;
        /* save token status in case we must abort */
        scanner.saveState();
        /* first pick out the time of day - we assume a HH (COLON|DOT) MM time */
        if (token.value.length() > 2) {
            //Definitely not an hour specification; probably a date or something.  Give up now
            return;
        }
        hour = Integer.parseInt(token.value);
        token = scanner.nextToken();

        spec.hour = hour;
        spec.min = minute;
        spec.sec = 0;
        if (spec.hour == 24) {
            spec.hour = 0;
            spec.day++;
        }
    }

    public TimeSpec parse() {
        long now = getTime();
        return parse(now);
    }

    public TimeSpec parse(long now) {
        int hr = 0;
        spec.localtime(now);
        token = scanner.nextToken();
        switch (token.id) {
            case TimeToken.PLUS:
            case TimeToken.MINUS:
                break; /* jump to OFFSET-SPEC part */
            case TimeToken.START:
                spec.type = TimeSpec.TYPE_START;
                /* FALLTHRU */
            case TimeToken.END:
                if (spec.type != TimeSpec.TYPE_START) {
                    spec.type = TimeSpec.TYPE_END;
                }
                /* FALLTHRU */
            case TimeToken.NOW:
                int time_reference = token.id;
                if (token.id != TimeToken.NOW) {
                    spec.year = spec.month = spec.day = spec.hour = spec.min = spec.sec = 0;
                }
                token = scanner.nextToken();
                if (token.id == TimeToken.PLUS || token.id == TimeToken.MINUS) {
                    break;
                }
                if (time_reference == TimeToken.START || time_reference == TimeToken.END) {
                    throw new RuntimeException("Words 'start' or 'end' MUST be followed by +|- offset");
                }
                else if (token.id != TimeToken.EOF) {
                    throw new RuntimeException("If 'now' or 'epoch' is followed by a token it must be +|- offset");
                }
                break;
            case TimeToken.NUMBER:
                timeOfDay();
                break;
            default:
                throw new RuntimeException("Unparsable time: " + token.value);
        }

        if (token.id == TimeToken.PLUS || token.id == TimeToken.MINUS) {
            scanner.setContext(false);
            while (token.id == TimeToken.PLUS || token.id == TimeToken.MINUS ||
                    token.id == TimeToken.NUMBER) {
                if (token.id == TimeToken.NUMBER) {
                    plusMinus(PREVIOUS_OP);
                }
                else {
                    plusMinus(token.id);
                }
                token = scanner.nextToken();
				/* We will get EOF eventually but that's OK, since
				token() will return us as many EOFs as needed */
            }
        }
        /* now we should be at EOF */
        if (token.id != TimeToken.EOF) {
            throw new RuntimeException("Unparsable trailing text: " + token.value);
        }
        return spec;
    }

    public static long getTime() {
        return (System.currentTimeMillis() + 500L) / 1000L;
    }
}
