package qianz.cloudapicommon.pojo.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationPO implements Serializable {
    private Long id;
    private String name;
    private String describe;
}
