package qianz.itineraryservice.exception.handler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qianz.cloudapicommon.pojo.result.Result;

/**
 * 处理feign异常
 * */
@RestControllerAdvice
@Slf4j
public class FeignExceptionHandler {
/*    @ExceptionHandler(FeignException.class)
    public Result<?> handleParamInvalidException(FeignException e) {
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }*/
}
