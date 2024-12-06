package qianz.cloudapicommon.pojo.PO;

import lombok.Data;

/**
* user表中的数据BO
* */
@Data
public class UserPO {
    private Long id;
    private String username;
    private String password;
}
