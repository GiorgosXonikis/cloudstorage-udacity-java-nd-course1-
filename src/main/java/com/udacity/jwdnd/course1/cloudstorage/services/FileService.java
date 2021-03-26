package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FileService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    private FileMapper fileMapper;
    private AuthenticationService authenticationService;

    public FileService(FileMapper fileMapper, AuthenticationService authenticationService) {
        this.fileMapper = fileMapper;
        this.authenticationService = authenticationService;
    }

    public ArrayList<File> getAllFiles() {
        return this.fileMapper.getAllFiles();
    }

    public int createFile(File file, User user) {
        file.setUserId(user.getUserId());
        return fileMapper.insertFile(file);
    }

    public int updateFile(File file, User user) {
        file.setUserId(user.getUserId());
        return fileMapper.updateFile(file);
    }

    public String deleteFile(int fileId) {
        try {
            this.fileMapper.deleteFile(fileId);
        } catch (MyBatisSystemException e) {
            this.logger.error("Error", e);
        }
        return "File deleted";
    }

}
