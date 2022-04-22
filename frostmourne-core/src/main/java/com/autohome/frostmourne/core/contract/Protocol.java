package com.autohome.frostmourne.core.contract;

import java.io.Serializable;

public class Protocol<T> implements Serializable {

    private static final long serialVersionUID = -3374377238757831251L;

    private int returncode;
    private String message;
    private T result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Protocol() {}

    public Protocol(T object) {
        this(object, 0, "ok");
    }

    public Protocol(int returncode, String message) {
        this(null, returncode, message);
    }

    public Protocol(T object, int returncode, String message) {
        this.result = object;
        this.returncode = returncode;
        this.message = message;
    }
}
