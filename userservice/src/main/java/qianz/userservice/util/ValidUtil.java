package qianz.userservice.util;

import lombok.extern.slf4j.Slf4j;
import qianz.cloudapicommon.exception.ParamInvalidException;
import qianz.cloudapicommon.pojo.BO.RegisterBO;

/***
* 验证前端发来的值的合法性工具类
* */
@Slf4j
public class ValidUtil {
    private static final Integer MAX_USERNAME_BYTE_LENGTH = 99;
    private static final Integer MAX_PASSWORD_BYTE_LENGTH = 99;

    /**
     * 验证RegisterBO是否合法
     *
     * @param registerBO 注册业务类
     * */
    public static void isRegisterBOValid(RegisterBO registerBO) {
        ValidUtil.isUsernameValid(registerBO.getUsername());
        ValidUtil.isPasswordValid(registerBO.getPassword());
    }

    private static void isUsernameValid(String username) {
        if (username == null || username.isEmpty()) throw new ParamInvalidException("用户名为空");
        if (username.getBytes().length > MAX_USERNAME_BYTE_LENGTH) throw new ParamInvalidException("用户名过长");
    }

    private static void isPasswordValid(String password) {
        if (password == null || password.isEmpty()) throw new ParamInvalidException("密码为空");
        if (password.getBytes().length > MAX_PASSWORD_BYTE_LENGTH) throw new ParamInvalidException("密码过长");
        if (password.equals("123456")) throw new ParamInvalidException("密码过于简单");
    }
}
