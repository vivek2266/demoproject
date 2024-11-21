package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.PdfGenerator;
import com.example.demo.entity.User;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>(); 
    private final AtomicLong idCounter = new AtomicLong(1); 

    @Autowired
    private PdfGenerator pdfGenerator;

    public User saveUser(User user) throws Exception {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        user.setId(idCounter.getAndIncrement());

        users.add(user);

        pdfGenerator.generatePdf(user);

        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }
}

