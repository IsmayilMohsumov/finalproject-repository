package com.example.finalproject.service;

import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository repository;
    private final NotificationService notificationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean createUser(User user) throws MessagingException, UnsupportedEncodingException {
        if(repository.findByEmail(user.getEmail()) != null){
            return true;
        }
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        notificationService.sendEmailWhenUserUpdated(user);
        repository.save(user);
        return false;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public boolean verify(String code) {
        User user = repository.findByVerificationCode(code);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            repository.save(user);

            return true;
        }
    }

    public void loginUser(User user) {



        }

    public User isUserExist(User user) {
        return repository.findByEmailAndPassword(user.getEmail(),user.getPassword());
    }

    public String getUserRole(User userFromService) {
        return repository.findRoleByUserId(userFromService.getId()).getRole();
    }
}
