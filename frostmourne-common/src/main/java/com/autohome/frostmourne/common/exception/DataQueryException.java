package com.autohome.frostmourne.common.exception;

/**
 * Data query exception.
 *
 * @author limbo
 * @since 2022/8/18 11:30
 */
public class DataQueryException extends Exception {

    public DataQueryException() {
        super();
    }

    public DataQueryException(String message) {
        super(message);
    }

    public DataQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataQueryException(Throwable cause) {
        super(cause);
    }

    protected DataQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
