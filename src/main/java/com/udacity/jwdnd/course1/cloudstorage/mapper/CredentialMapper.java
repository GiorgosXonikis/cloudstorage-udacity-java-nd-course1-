package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIAL WHERE user_id = #{userId}")
    ArrayList<Credential> getAllCredentials(int userId);

    @Select("SELECT * FROM CREDENTIAL WHERE id = #{id}")
    Credential getCredential(Integer id);

    @Insert("INSERT INTO CREDENTIAL (url, username, key, password, user_id) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCredential(Credential credentials);

    @Update("UPDATE CREDENTIAL SET url = #{url}, username = #{username}, key = #{key}, password = #{password}, user_id = #{userId} WHERE id = #{id}")
    int updateCredential(Credential credentials);

    @Delete("DELETE FROM CREDENTIAL WHERE id = #{id}")
    void deleteCredential(Integer id);

}
