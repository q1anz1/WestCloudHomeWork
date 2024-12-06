package qianz.cloudapicommon.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Result中的基本
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base {
    private Integer code;
    private String msg;
}
