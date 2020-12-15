package com.computacenter.controller.admin;

import com.computacenter.entity.User;
import com.computacenter.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    private static final String LOGIN = "admin/login";
    private static final String REDIRECT_DETAIL = "redirect:/admin/detail";
    private static final String REDIRECT_ADMIN = "redirect:/admin";
    private static final String REDIRECT_ = "redirect:/";

    @Autowired
    UserService service;

    @GetMapping
    @ApiOperation("If session does not have user info, then go to login page.") //comment
    public String toLogin(HttpSession session){
        if (session.getAttribute("user") != null){
            return REDIRECT_DETAIL;
        }
        return LOGIN;
    }

    @GetMapping("/login")
    @ApiOperation("Redirect to /admin to avoid 405 error") //comment
    public String toLogin2(){
        return "redirect:/admin";
    }



    @PostMapping("/login") //@RequestParam: param here matches the param we get from user input
    @ApiOperation("If username and password are valid, store it in session and go to detail page. Otherwise redirect to /admin.") //comment
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        if (username.isBlank() || password.isBlank()){
            attributes.addFlashAttribute("message", "Benutzername oder Passwort leer.");
            return REDIRECT_ADMIN;
        }
        User user = service.checkUser(username, password);
        if (user != null){
            user.setPassword(null); //don't store password, not safe
            session.setAttribute("user", user);
            return REDIRECT_DETAIL;
        } else {
            //addFlashAttribute:
            //1. can pass any data type(not only primitive types or string)
            //2. after it is fetched, it will be cleared, suitable for feedback message of form
            attributes.addFlashAttribute("message", "Benutzername oder Passwort falsch.");
            //here we can not use Model, cuz it can't send data when we use redirect
            return REDIRECT_ADMIN;
        }

    }



    @GetMapping("/logout")
    @ApiOperation("Log out, remove user info in session and go to index page.")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return REDIRECT_;
    }







}
