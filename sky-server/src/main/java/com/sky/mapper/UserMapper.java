package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User getByLiffId(String openid);
    void insert(User user);

    void updateOs(String openid,String phone);

}
