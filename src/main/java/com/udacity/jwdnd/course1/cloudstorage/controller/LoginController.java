//package com.udacity.jwdnd.course1.cloudstorage.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    @GetMapping()
//    public String loginView() {
//        return "login";
//    }
//
//}


package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping(value = {"/signup-success"})
    public String loginViewAfterSignup(Model model) {
        Boolean showSuccessMessage;
        model.addAttribute("showSuccessMessage", true);

        return "login";
    }

    @PostMapping("/login")
    public String loginSuccessful() {
        return "home";
    }

}
