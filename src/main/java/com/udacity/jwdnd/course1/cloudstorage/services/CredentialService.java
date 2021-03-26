package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public ArrayList<Credential> getAllCredentials(User user) {
        return this.credentialMapper.getAllCredentials(user.getUserId());
    }

    public int createCredential(Credential credential, User user) {
        Credential securedCredential = this.getSecuredCredential(credential, user);
        return this.credentialMapper.insertCredential(securedCredential);
    }

    public int updateCredential(Credential credential, User user) {
        Credential securedCredential = this.getSecuredCredential(credential, user);
        return this.credentialMapper.updateCredential(securedCredential);
    }

    public String deleteCredential(int credentialId) {
        try {
            this.credentialMapper.deleteCredential(credentialId);
        } catch (MyBatisSystemException e) {
            this.logger.error("Error", e);
        }
        return "Credential deleted";
    }

    private Credential getSecuredCredential(Credential credential, User user) {
        credential.setUserId(user.getUserId());

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = this.encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

        return credential;
    }

}
