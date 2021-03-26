package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILE")
    ArrayList<File> getAllFiles();

    @Select("SELECT * FROM FILE WHERE id = #{id}")
    File getFile(Integer id);

    @Insert("INSERT INTO FILE (file_name, content_type, file_size, file_data, user_id) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertFile(File file);

    @Update("UPDATE FILE SET file_name = #{fileName}, content_type = #{contentType}, file_size = #{fileSize}, file_data = #{fileData}, user_id = #{userId} WHERE id = #{id}")
    int updateFile(File file);

    @Delete("DELETE FROM FILE WHERE id = #{id}")
    void deleteFile(Integer id);

}

