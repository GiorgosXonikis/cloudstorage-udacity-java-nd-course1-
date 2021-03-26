package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class ValidationService {
    Map<String, String> validationMessagesRegistry = new HashMap<String, String>();
    public String status = "";
    public String message = "";

    public ValidationService(Map<String, String> validationMessages) {
        this.validationMessagesRegistry = validationMessages;
        this.putValidationMessages();
    }

    public void validate(String code) {
        this.status = "";
        this.message = "";

        if (code.contains("Error")) {
            this.status = "error";
        } else {
            this.status = "success";
        }

        this.message = this.validationMessagesRegistry.get(code);
    }

    @PostConstruct
    private void putValidationMessages() {
        this.validationMessagesRegistry.put("noteCreationSuccess", "Note created successfully");
        this.validationMessagesRegistry.put("noteCreationError", "Note creation Error");
        this.validationMessagesRegistry.put("noteUpdateSuccess", "Note updated successfully");
        this.validationMessagesRegistry.put("noteUpdateError", "Note updated Error");
        this.validationMessagesRegistry.put("noteDeletionSuccess", "Note deleted successfully");
        this.validationMessagesRegistry.put("noteDeletionError", "Note deletion error");


        this.validationMessagesRegistry.put("fileCreationSuccess", "File created successfully");
        this.validationMessagesRegistry.put("fileCreationError", "File creation Error");
        this.validationMessagesRegistry.put("fileUpdateSuccess", "File updated successfully");
        this.validationMessagesRegistry.put("fileUpdateError", "File updated Error");
        this.validationMessagesRegistry.put("fileDeletionSuccess", "File deleted successfully");
        this.validationMessagesRegistry.put("fileDeletionError", "File deleted successfully");

        this.validationMessagesRegistry.put("credentialsCreationSuccess", "Credential created successfully");
        this.validationMessagesRegistry.put("credentialsCreationError", "Credential creation Error");
        this.validationMessagesRegistry.put("credentialUpdateSuccess", "Credential updated successfully");
        this.validationMessagesRegistry.put("credentialUpdateError", "Credential updated Error");
        this.validationMessagesRegistry.put("credentialDeletionSuccess", "Credential deleted successfully");
        this.validationMessagesRegistry.put("credentialDeletionError", "Credential deleted successfully");
    }

}
