package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;


@Controller
public class HomeController {
    public String successMessage;
    public String errorMessage;

    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private AuthenticationService authenticationService;

    public HomeController(UserService userService, NoteService noteService, FileService fileService, CredentialService credentialService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/result")
    public String getResultPage(Model model) {
        model.addAttribute("successMessage", this.successMessage);
        model.addAttribute("errorMessage", this.errorMessage);

        return "result";
    }

    @GetMapping("/home")
    public String getHomePage(Note note, Credential credential, File file, Model model, Authentication authentication) {
        this.successMessage = null;
        this.errorMessage = null;

        String username = authentication.getName();
        User user = this.userService.getUser(username);

        ArrayList<Credential> credentials = this.credentialService.getAllCredentials(user);
        ArrayList<Note> notes = this.noteService.getAllNotes(user);
        ArrayList<File> files = this.fileService.getAllFiles();

        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("files", files);

        return "home";
    }

    /**
     * Note
     */
    @PostMapping("/note")
    public String createOrUpdateNote(Authentication auth, Note note) {
        String username = auth.getName();
        User user = this.userService.getUser(username);

        if (!auth.isAuthenticated()) return "redirect:/login";

        if (note.getId() != null) {
            if (this.noteService.updateNote(note, user) != 0) {
                this.successMessage = "Note updated successfully.";
            } else {
                this.errorMessage = "Note update error.";
            }

        } else {
            if (this.noteService.createNote(note, user) != 0) {
                this.successMessage = "Note created successfully.";
            } else {
                this.errorMessage = "Note creation error.";
            }
        }

        return "redirect:/result";
    }

    @GetMapping(value = {"/delete-note/{id}"})
    public String deleteNote(@PathVariable("id") String id) {
        String status = this.noteService.deleteNote(Integer.parseInt(id));

        if (status.equals("success")) {
            this.successMessage = "Note deleted successfully.";
        }

        if (status.equals("error")) {
            this.errorMessage = "Note deletion error.";
        }

        return "redirect:/result";
    }

    /**
     * Credentials
     */
    @PostMapping("/credential")
    public String createOrUpdateCredential(Credential credential, Authentication authentication) {
        String username = authentication.getName();
        User user = this.userService.getUser(username);

        if (credential.getId() != null) {
            this.credentialService.updateCredential(credential, user);
        } else {
            this.credentialService.createCredential(credential, user);
        }
        return "redirect:/result";
    }

    @GetMapping(value = {"/delete-credential/{id}"})
    public String deleteCredential(@PathVariable("id") String id) {
        this.credentialService.deleteCredential(Integer.parseInt(id));
        return "redirect:/result";
    }

    /**
     * File
     */
    @PostMapping("/file")
    public String postFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = this.userService.getUser(username);

        File file = new File(
                null,
                fileUpload.getOriginalFilename(),
                fileUpload.getContentType(),
                String.valueOf(fileUpload.getSize()),
                fileUpload.getBytes(),
                null
        );

        this.fileService.createFile(file, user);

        return "redirect:home";
    }

//    @GetMapping("/file/{fileId}")
//    public ResponseEntity<Resource> getFile(@PathVariable Long fileId) {
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(file.getType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
//                + file.getName() + "\"")
//                .body(new ByteArrayResource(file.getData()));
//    }

    @GetMapping(value = {"/delete-file/{id}"})
    public String deleteFile(@PathVariable("id") String id) {
        this.fileService.deleteFile(Integer.parseInt(id));
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutView() {
        return "redirect:/login?logout";
    }

}
