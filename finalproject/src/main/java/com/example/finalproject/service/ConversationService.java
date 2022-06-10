package com.example.finalproject.service;

import com.example.finalproject.entity.Conversation;
import com.example.finalproject.entity.QnA;
import com.example.finalproject.repository.ConversationRepository;
import com.example.finalproject.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private String USERNAME;
    private String ID_NUMBER;
    private final QnARepository qnARepository;
    private final ConversationRepository conversationRepository;

    public String process(Conversation conversation) {
        String messageFromUser = conversation.getMessage();
//        String check = checkMessage(messageFromUser);
//        if (!check.isEmpty()){
//            return check;
//        }
        QnA byQuestion = qnARepository.findByQuestion(messageFromUser);
        if(byQuestion == null){
            return "Sorry I have no answer";
        }
        Conversation conversation1 = new Conversation();
        conversation1.setQuestion(byQuestion);
        conversation1.setUsernameID(conversation.getUsernameID());
        conversation1.setMessage(conversation.getMessage());
        conversationRepository.save(conversation1);


        return byQuestion.getAnswer();
    }

    public String checkMessage(String message){
        QnA questionLikeText = qnARepository.findQuestionLikeText(message);
        return questionLikeText.getAnswer();
    }
}
