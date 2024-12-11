package qianz.cloudapicommon.pojo.PO;

import lombok.Data;

import java.io.Serializable;

/**
* user表中的数据BO
* */
@Data
public class UserPO implements Serializable {
    private Long id;
    private String username;
    private String password;
}
