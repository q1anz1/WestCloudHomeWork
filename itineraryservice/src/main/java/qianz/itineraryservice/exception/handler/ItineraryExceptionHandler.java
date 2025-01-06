package qianz.itineraryservice.exception.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.cloudapicommon.exception.handler.GlobalExceptionHandler;
import qianz.cloudapicommon.pojo.result.Result;

import java.net.UnknownHostException;

/**
* 全局异常处理器
* */
@Slf4j
@RestControllerAdvice
public class ItineraryExceptionHandler extends GlobalExceptionHandler {
    @Override
    public Result<?> handleParamInvalidException(ParamInvalidException e, HttpServletResponse response) {
        return super.handleParamInvalidException(e, response);
    }
}
