package qianz.cloudapicommon.pojo.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryPO {
    private Long id;
    private Long userId;
    private Long destinationId;
    private String destinationName;
    private Date time;

    public ItineraryPO(Long userId, Long destinationId, String destinationName, Date time) {
        this.userId = userId;
        this.destinationId = destinationId;
        this.time = time;
        this.destinationName = destinationName;
    }
}
