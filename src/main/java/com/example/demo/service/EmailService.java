package com.example.demo.service;

import org.springframework.stereotype.Service;

public interface EmailService {

    void sendMail(String email, String itemName);

}
