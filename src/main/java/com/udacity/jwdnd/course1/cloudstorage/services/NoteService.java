package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    private NoteMapper noteMapper;
    private AuthenticationService authenticationService;

    public NoteService(NoteMapper noteMapper, AuthenticationService authenticationService, ValidationService validationService) {
        this.noteMapper = noteMapper;
        this.authenticationService = authenticationService;
    }

    public ArrayList<Note> getAllNotes(User user) {
        System.out.println(user.getUserId());
        System.out.println(user.getUsername());

        return this.noteMapper.getAllNotes(user.getUserId());
    }

    public int createNote(Note note, User user) {
        note.setUserId(user.getUserId());
        return this.noteMapper.insertNote(note);
    }

    public int updateNote(Note note, User user) {
        note.setUserId(user.getUserId());
        return this.noteMapper.updateNote(note);
    }

    public String deleteNote(int noteId) {
        try {
            this.noteMapper.deleteNote(noteId);
            return "success";
        } catch (MyBatisSystemException e) {
            this.logger.error("Error", e);
            return "error";
        }
    }

}
