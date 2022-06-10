package com.example.finalproject.service;

import com.example.finalproject.entity.QnA;
import com.example.finalproject.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final QnARepository qnARepository;
    public void saveMessage(String messageFromUser, HttpServletRequest request) {
        System.out.println(request.getRequestedSessionId());
        System.out.println(request.getContextPath());
        System.out.println(request.getSession());
        QnA entity = new QnA();
        String randomCode = RandomString.make(8);
//        entity.setChatId(randomCode);
//        entity.setText(messageFromUser);
        qnARepository.save(entity);
    }

    public void saveQnAObject(QnA qnA) {
        qnARepository.save(qnA);
    }

    public QnA findObjectById(Long questionID) {
        return qnARepository.findById(questionID).get();
    }

    public void deleteEntityById(Long questionID) {
        qnARepository.deleteById(questionID);
    }

    @Transactional
    public void getAndUpdateById(QnA qnA) {
        qnARepository.editQuestionById(qnA.getQuestion(), qnA.getId());
        qnARepository.editAnswerById(qnA.getAnswer(), qnA.getId());
    }

    public List<QnA> findAll() {
        return qnARepository.findAll();
    }
}
