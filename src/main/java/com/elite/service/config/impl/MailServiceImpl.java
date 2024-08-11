package com.elite.service.config.impl;

import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.service.config.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendUserRegisterWelcomeMail(String to, String subject, String username) throws MessagingException {
        log.info(MessageResource.getMessage(ESLog.ES_010), username);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        Context context = new Context();
        context.setVariable("username", username);
        String htmlContent = templateEngine.process("registration", context);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
        log.info(MessageResource.getMessage(ESLog.ES_003), username);
    }
}
