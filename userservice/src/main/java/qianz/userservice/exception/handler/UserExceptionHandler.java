package qianz.userservice.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.cloudapicommon.exception.handler.GlobalExceptionHandler;
import qianz.cloudapicommon.pojo.result.Result;

/**
* 全局异常处理器
* */
@RestControllerAdvice
public class UserExceptionHandler extends GlobalExceptionHandler {

}
