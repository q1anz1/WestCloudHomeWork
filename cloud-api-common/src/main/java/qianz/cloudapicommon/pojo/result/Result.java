package qianz.cloudapicommon.pojo.result;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
* 统一响应结果类
* */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Base base;
    private T data;

    /**
    * 请求成功200，无数据返回
    *
    * @return Result类
    * */
    public static<T> Result<T> ok() {
        return ok(null);
    }

    /**
     * 请求成功200，包含数据返回
     *
     * @param object 数据
     * @return Result类
     * */
    public static<T> Result<T> ok(T object) {
        Base base = new Base();
        base.setCode(HttpStatus.OK.value());
        base.setMsg(HttpStatus.OK.getReasonPhrase());
        Result<T> result= new Result<>();
        result.setBase(base);
        result.setData(object);
        return result;
    }

    /**
    * 请求错误
    *
    * @param code 错误代码 msg 错误消息
    * @return Result类
    * */
    public static<T> Result<T> error(int code , String msg) {
        Base base = new Base();
        base.setCode(code);
        base.setMsg(msg);
        Result<T> result= new Result<>();
        result.setBase(base);
        return result;
    }

    /**
    * 把Result类序列化成JSON字符串
    *
    * @return JSON序列化的字符串
    * */
    private String asJsonString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
