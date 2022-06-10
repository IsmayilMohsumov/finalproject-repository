package com.example.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255,unique = true)
    private String question;

    @Column(length = 255)
    private String answer;

    @OneToOne(mappedBy = "question")
    private Conversation conversation;


}
