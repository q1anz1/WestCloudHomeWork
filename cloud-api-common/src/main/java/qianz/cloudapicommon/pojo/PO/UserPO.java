package qianz.cloudapicommon.pojo.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* user表中的数据BO
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPO implements Serializable {
    private Long id;
    private String username;
    private String password;
}
