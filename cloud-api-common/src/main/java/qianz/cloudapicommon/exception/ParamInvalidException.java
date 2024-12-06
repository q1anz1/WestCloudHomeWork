package qianz.cloudapicommon.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
* 前端参数错误异常
* */
@Getter
public class ParamInvalidException extends MyRuntimeException{
    public ParamInvalidException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
