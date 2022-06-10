package com.SATBOT.api.services;

import com.SATBOT.api.entities.Conversation;
import com.SATBOT.api.entities.QnA;
import com.SATBOT.api.repos.ConversationRepository;
import com.SATBOT.api.repos.QnARepository;
import com.SATBOT.api.repos.RoleRepository;
import com.SATBOT.api.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final RoleRepository roleRepository;
    private final QnARepository qnARepository;


    public List<Conversation> getAdminConversations() {
        return conversationRepository.findAll();
    }

    public List<Conversation> getAdminMessages(String username_id) {
        return conversationRepository.findByUsernameID(username_id);
    }
}
