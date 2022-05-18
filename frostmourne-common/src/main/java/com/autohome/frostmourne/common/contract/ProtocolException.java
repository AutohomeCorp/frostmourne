package com.autohome.frostmourne.common.contract;

public class ProtocolException extends RuntimeException {

    private int returncode;

    private String message;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProtocolException(int returncode, String message) {
        super(message);
        this.returncode = returncode;
        this.message = message;
    }

    public ProtocolException(int returncode, String message, Throwable ex) {
        super(message, ex);
        this.returncode = returncode;
        this.message = message;
    }

    public Protocol toProtocol() {
        return new Protocol(returncode, message);
    }
}
