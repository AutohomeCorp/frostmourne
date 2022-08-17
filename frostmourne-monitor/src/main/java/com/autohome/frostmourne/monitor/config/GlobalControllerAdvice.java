package com.autohome.frostmourne.monitor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.common.contract.Protocol;
import com.autohome.frostmourne.common.contract.ProtocolException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    private static final Protocol DEFAULT_ERROR_PROTOCOL =
            new Protocol(HttpStatus.INTERNAL_SERVER_ERROR.value(), "unknown exception");

    @ExceptionHandler({ProtocolException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Protocol handleProtocolException(ProtocolException protocolException) {
        LOGGER.error("global catch exception", protocolException);
        return protocolException.toProtocol();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Protocol handleException(Exception exception) {
        LOGGER.error("global catch exception", exception);
        return DEFAULT_ERROR_PROTOCOL;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Protocol handleInvalidParamReqException(final HttpServletRequest req, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 异常中存在错误消息则使用，否则使用默认错误消息
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (!allErrors.isEmpty()) {
                ObjectError objectError = allErrors.get(0);
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    return Protocol.fail(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                }
                return Protocol.fail(objectError.getDefaultMessage());
            }
        }
        return Protocol.fail("请求异常，请检查请求参数");
    }
}
