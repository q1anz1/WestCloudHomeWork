package qianz.itineraryservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import qianz.cloudapicommon.pojo.PO.ItineraryPO;

@Mapper
public interface ItineraryMapper {
    void insertItineraryPO(ItineraryPO itineraryPO);
}
