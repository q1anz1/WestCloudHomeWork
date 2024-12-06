package qianz.userservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qianz.cloudapicommon.pojo.PO.UserPO;

/**
* UserMapper
* */
@Mapper
public interface UserMapper {
    @Select("SELECT id, username, password FROM west_cloud.user WHERE username=#{username}")
    UserPO selectUserPOByUsername(String username);

    @Insert("INSERT INTO west_cloud.user(username, password) VALUES(#{username}, #{encodedPassword})")
    void insertUserPO(String username, String encodedPassword);
}
