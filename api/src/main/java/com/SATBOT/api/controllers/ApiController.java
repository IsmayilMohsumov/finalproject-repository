package com.SATBOT.api.controllers;

import com.SATBOT.api.entities.Conversation;
import com.SATBOT.api.services.CommonService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class ApiController {

    @Autowired
    CommonService commonService;


    @ResponseBody
    @RequestMapping(value = "/api/admin/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversations(){
        List<Conversation> response = commonService.getAdminConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/admin/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> messages(@RequestBody String username_id){
        List<Conversation> response = commonService.getAdminMessages(username_id );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }



}