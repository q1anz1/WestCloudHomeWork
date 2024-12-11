package cn.qianz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qianz.cloudapicommon.pojo.PO.DestinationPO;

@Mapper
public interface DestinationMapper {
    @Select("SELECT id, name, `describe` FROM west_cloud.destination WHERE id=#{id}")
    DestinationPO selectDestinationPOById(Long id);
}
