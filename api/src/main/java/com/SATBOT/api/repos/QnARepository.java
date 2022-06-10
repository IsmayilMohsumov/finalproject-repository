package com.SATBOT.api.repos;

import com.SATBOT.api.entities.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface QnARepository extends JpaRepository<QnA,Long> {

    @Query("SELECT q FROM QnA q WHERE q.question = ?1")
    public QnA findByQuestion(String code);

    @Query("SELECT q FROM QnA q WHERE q.question like %:message%")
    public QnA findQuestionLikeText(String message);

//    update qna q set q.question='test' and q.answer='no' where q.id=1;
    @Modifying
    @Transactional
    @Query("UPDATE QnA q SET q.question=?1 where q.id=?2")
    public void editQuestionById(String question,Long id);

    @Modifying
    @Transactional
    @Query("UPDATE QnA q SET q.answer=?1 where q.id=?2")
    public void editAnswerById(String answer, Long id);


}
