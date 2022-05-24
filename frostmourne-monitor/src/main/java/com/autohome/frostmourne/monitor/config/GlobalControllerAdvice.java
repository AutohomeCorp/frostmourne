package com.autohome.frostmourne.monitor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.autohome.frostmourne.common.contract.Protocol;
import com.autohome.frostmourne.common.contract.ProtocolException;

@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    private static final Protocol DEFAULT_ERROR_PROTOCOL = new Protocol(500, "unknown exception");

    @ExceptionHandler({ProtocolException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Protocol handleProtocolException(ProtocolException protocolException) {
        LOGGER.error("global catch exception", protocolException);
        return protocolException.toProtocol();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Protocol handleExceptoin(Exception exception) {
        LOGGER.error("global catch exception", exception);
        return DEFAULT_ERROR_PROTOCOL;
    }
}
