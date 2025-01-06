package qianz.itineraryservice.exception.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qianz.cloudapicommon.pojo.result.Result;
import qianz.frequencyspringbootstarter.exception.FrequencyControlException;

/**
 *
 */
@Slf4j
@RestControllerAdvice
public class FrequencyControlExceptionHandler {
    @ExceptionHandler(FrequencyControlException.class)
    public Result<?> handleParamInvalidException(FrequencyControlException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        return Result.error(HttpStatus.TOO_MANY_REQUESTS.value(), e.getMessage());
    }
}
