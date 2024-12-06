package qianz.cloudapicommon.pojo.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
* 进行用户注册的业务的BO
* */
@Data
@AllArgsConstructor
public class RegisterBO {
    private String username;
    private String password;
}
