package qianz.cloudapicommon.pojo.PO;

import lombok.Data;

import java.io.Serializable;
@Data
public class DestinationPO implements Serializable {
    private Long id;
    private String name;
    private String describe;
}
