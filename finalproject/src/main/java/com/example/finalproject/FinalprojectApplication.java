package com.example.finalproject;

import com.example.finalproject.entity.QnA;
import com.example.finalproject.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class FinalprojectApplication implements CommandLineRunner {

	private final QnARepository qnARepository;
	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		QnA question = new QnA();
//		question.setQuestion("What is Java?");
//		question.setAnswer("Java is a programming language");
//		QnA question1 = new QnA();
//		question1.setQuestion("Hi");
//		question1.setAnswer("Hi dear user how can I help you?");
//		QnA question2 = new QnA();
//		question2.setQuestion("What is your name?");
//		question2.setAnswer("My name is Satbot");
//		List<QnA> questions = new ArrayList() {{
//			add(question);
//			add(question1);
//			add(question2);
//		}};
//
//		qnARepository.saveAll(questions);

	}
}
