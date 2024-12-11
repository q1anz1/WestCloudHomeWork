package qianz.cloudapicommon.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* Result中的基本
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base implements Serializable {
    private Integer code;
    private String msg;
}
