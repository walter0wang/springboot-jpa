package com.epg.act.web.controller;

import com.epg.act.web.responseVo.ResultBean;
import com.epg.act.web.responseVo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /*@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResultBean<?> handlerException(Exception e) {
        logger.error(e.toString());
//        // custom exception
//        if (e instanceof CheckException) {
//            CheckException checkException = (CheckException) e;
//            result.setCode(checkException.getCode());
//            result.setMsg(e.getMessage());
//        } else {
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultCode.SYSTEM_ERROR.getCode());
//        }
        return new ResultBean<>(e);
    }*/



    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResultBean unexpectedType(UnexpectedTypeException exception){
        log.error("UnexpectedTypeException", exception);
        return new ResultBean<>(ResultCode.MULTI_CHECK, exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultBean messageNotReadable(HttpMessageNotReadableException exception){
        log.error("error json type", exception);
        return new ResultBean<>(ResultCode.ERROR_JSON, exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResultBean<?> ex(MethodArgumentNotValidException exception) {
        log.error("params not valid", exception);
        BindingResult bindingResult = exception.getBindingResult();
        return new ResultBean<>(ResultCode.CHECK_ERROR, getErrors(bindingResult));
    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
}
