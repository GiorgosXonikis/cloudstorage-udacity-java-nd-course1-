package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTE WHERE user_id = #{userId}")
    ArrayList<Note> getAllNotes(int userId);

    @Select("SELECT * FROM NOTE WHERE id = #{id}")
    Note getNote(Integer id);

    @Insert("INSERT INTO NOTE (note_title, note_description, user_id) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertNote(Note note);

    @Update("UPDATE NOTE SET note_title = #{noteTitle}, note_description = #{noteDescription}, user_id = #{userId} WHERE id = #{id}")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTE WHERE id = #{id}")
    void deleteNote(Integer id);

}
