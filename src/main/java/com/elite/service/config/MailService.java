package com.elite.service.config;


public interface MailService {

    void sendUserRegisterWelcomeMail(String to, String subject, String username);
}
