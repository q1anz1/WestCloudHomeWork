package qianz.itineraryservice.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qianz.cloudapicommon.pojo.PO.ItineraryPO;

import java.util.List;

@Mapper
public interface ItineraryMapper {
    void insertItineraryPO(ItineraryPO itineraryPO);

    @Select("SELECT id, user_id, destination_id, destination_name, time FROM west_cloud.itinerary WHERE user_id=#{userId}")
    List<ItineraryPO> getItineraryListByUserId(Long userId);

    @Select("SELECT id, user_id, destination_id, destination_name, time FROM west_cloud.itinerary WHERE id=#{itineraryId}")
    ItineraryPO getItineraryByItineraryId(Long itineraryId);

    @Delete("DELETE FROM west_cloud.itinerary WHERE id=#{itineraryId}")
    void deleteItineraryByItineraryId(Long itineraryId);
}
