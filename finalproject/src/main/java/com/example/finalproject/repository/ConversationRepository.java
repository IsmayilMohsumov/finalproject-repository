package com.example.finalproject.repository;

import com.example.finalproject.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query(value = "select * from conversation c inner join qna q  on q.id = c.question_id", nativeQuery = true )
    public List<Conversation> getAllByInnerJoin();
}
