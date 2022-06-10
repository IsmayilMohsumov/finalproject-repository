package com.example.finalproject.controller;

import com.example.finalproject.entity.Conversation;
import com.example.finalproject.entity.QnA;
import com.example.finalproject.entity.User;
import com.example.finalproject.service.ConversationService;
import com.example.finalproject.service.QnAService;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final QnAService qnAService;
    private final ConversationService conversationService;

    @SneakyThrows
    @PostMapping("/signup")
    public String registration(User user, Model model){
        Boolean isUserExist = userService.createUser(user);
        if (isUserExist){
            model.addAttribute("userEmailExists","The email is taken");
            return "register";
        }
        model.addAttribute("email",user.getEmail());
        return "email-sent";
    }

    @SneakyThrows
    @PostMapping("/signin")
    public String login(User user, Model model){
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        User userFromService = userService.isUserExist(user);
        System.out.println(userFromService);
        if(userFromService == null){
            model.addAttribute("status","Wrong credentials");
            return "login";
        }else if(!userFromService.isEnabled()){
            model.addAttribute("status","User is not verified account");
            return "login";
        }else{
            model.addAttribute("status",userFromService);
            List<QnA> all = qnAService.findAll();
            model.addAttribute("questionsList",all);
            return "admin-panel";
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {

        if (userService.verify(code)) {
            return "verify-success";
        } else {
            return "verify-fail";
        }
    }

//    @PostMapping("/checkuser")
//    public ResponseEntity<Object> ajax(HttpServletRequest request) {
//        String message = " Hi how can I help you?";
//        String messageFromUser = request.getParameter("username");
//        System.out.println(messageFromUser);
////        qnAService.saveMessage(messageFromUser,request);
//        return new ResponseEntity<Object>(message, HttpStatus.OK);
//    }

    @PostMapping("/admin/chat")
    public ResponseEntity<Object> receiveObjectFromAjax(@RequestBody Conversation conversation, HttpServletRequest request) {
        System.out.println("SESSION ID IS HERE:"+conversation.getUsernameID());

        System.out.println("MESSAGE ID IS HERE:"+conversation.getMessage());
        String message = conversationService.process(conversation);
        System.out.println("Message from repository is"+message);
        return new ResponseEntity<Object>(message, HttpStatus.OK);
    }

    @PostMapping("/admin/add-question")
    public ResponseEntity<Object> addQuestionByAjaxRequest(@RequestBody QnA qnA, HttpServletRequest request) {
//        System.out.println("Question ---------------"+qnA.getQuestion());
//        System.out.println(qnA.getAnswer());
        qnAService.saveQnAObject(qnA);
        return new ResponseEntity<Object>("Perfect", HttpStatus.OK);
    }

    @PostMapping("/admin/editQuestionById")
    public ResponseEntity<Object> getDataForEditModalByIdFromAjaxRequest(HttpServletRequest request) {
        System.out.println(request.getParameter("questionID"));
        Long questionID = Long.valueOf(request.getParameter("questionID"));
        QnA qnA = qnAService.findObjectById(questionID);
        return new ResponseEntity<Object>(qnA, HttpStatus.OK);
    }

    @PostMapping("/admin/updateQuestion")
    public ResponseEntity<Object> editQuestionByIdFromAjaxRequest(@RequestBody QnA qnA,HttpServletRequest request) {
        System.out.println("Question ---------------"+qnA.getId());
        System.out.println("Question ---------------"+qnA.getQuestion());
        System.out.println(qnA.getAnswer());
        qnAService.getAndUpdateById(qnA);
        return new ResponseEntity<Object>("Perfect", HttpStatus.OK);
    }

    @PostMapping("/admin/delete-question-by-id")
    public ResponseEntity<Object> deleteQuestionByIdFromAjaxRequest(HttpServletRequest request) {
        System.out.println(request.getParameter("questionID"));
        Long questionID = Long.valueOf(request.getParameter("questionID"));
        qnAService.deleteEntityById(questionID);
        return new ResponseEntity<Object>("deleted", HttpStatus.OK);
    }
}
