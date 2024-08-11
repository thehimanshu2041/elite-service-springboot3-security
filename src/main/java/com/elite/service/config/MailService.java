package com.elite.service.config;

import jakarta.mail.MessagingException;

public interface MailService {

    void sendUserRegisterWelcomeMail(String to, String subject, String username) throws MessagingException;
}
