package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE username = #{username}")
    User getUser(String username);

    @Select("SELECT * FROM USER")
    ArrayList<User> getAllUsers();

    @Insert("INSERT INTO USER (username, salt, password, first_name, last_name) VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

}
