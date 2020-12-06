package com.computacenter.controller.admin;

import com.computacenter.pojo.User;
import com.computacenter.service.UserService;
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

    @Autowired
    UserService service;

    @GetMapping
    public String toLogin(){
        return "../admin/login";
    }

    @GetMapping("/login")
    public String toLogin2(){
        return "redirect:/admin";
    }



    @PostMapping("/login") //@RequestParam: param here matches the param we get from user input
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = service.checkUser(username, password);
        if (user != null){
            user.setPassword(null); //don't store password, not safe
            session.setAttribute("user", user);
            return "redirect:/admin/detail";
        } else {
            //addFlashAttribute:
            //1. can pass any data type(not only primitive types or string)
            //2. after it is fetched, it will be cleared, suitable for feedback message of form
            attributes.addFlashAttribute("message", "Username oder Password falsch.");
            //here we can not use Model, cuz it can't send data when we use redirect
            return "redirect:/admin";
        }

    }



    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }




}
