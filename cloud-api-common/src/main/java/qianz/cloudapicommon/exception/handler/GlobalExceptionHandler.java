package qianz.cloudapicommon.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.cloudapicommon.pojo.result.Result;

/**
* 全局异常处理器
* */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ParamInvalidException.class)
    public Result<?> handleParamInvalidException(ParamInvalidException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}
