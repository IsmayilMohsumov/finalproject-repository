package com.example.finalproject.controller;

import com.example.finalproject.entity.Conversation;
import com.example.finalproject.entity.QnA;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.ConversationRepository;
import com.example.finalproject.repository.QnARepository;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final QnARepository qnARepository;
    private final ConversationRepository conversationRepository;
    private final UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/admin")
    public String adminPage(Model model){
        List<QnA> all = qnARepository.findAll();
        System.out.println(all);
        model.addAttribute("questionsList",all);
        return "admin-panel";
    }

    @GetMapping("/user")
    public String userPage(Model model){
        return "user-panel";
    }

    @GetMapping("/admin/show-questions")
    public String showQuestions(Model model){
        List<Conversation> allByInnerJoin = conversationRepository.getAllByInnerJoin();
//        System.out.println(allByInnerJoin.get(0).getQuestion().getAnswer());
        model.addAttribute("allRelatedChat",allByInnerJoin);
        return "admin-panel-show-questions";
    }

    @GetMapping("/admin/users")
    public String getUsersForAdminPanel(Model model){
        List<User> all = userService.getAll();
        System.out.println(all.get(0).isEnabled());
//        System.out.println(allByInnerJoin.get(0).getQuestion().getAnswer());
        model.addAttribute("allUsers",all);
        return "admin-panel-show-users";
    }

}
