package qianz.cloudapicommon.exception;

import lombok.Getter;
/**
* 一个自定义运行时异常，作为父类
* */
@Getter
public class MyRuntimeException extends RuntimeException{
    private final Integer code;

    public MyRuntimeException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
