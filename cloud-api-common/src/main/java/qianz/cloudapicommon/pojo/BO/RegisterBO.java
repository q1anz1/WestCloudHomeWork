package qianz.cloudapicommon.pojo.BO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
* 进行用户注册的业务的BO
* */
@Data
@AllArgsConstructor
public class RegisterBO implements Serializable {
    private String username;
    private String password;
}
