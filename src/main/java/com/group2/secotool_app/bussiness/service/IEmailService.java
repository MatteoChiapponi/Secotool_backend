package com.group2.secotool_app.bussiness.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMultipart;


public interface IEmailService {
    void sendSimpleEmail(String to, String subject, String body);

    void sendHtmlEmail(String to, String subject, String body, MimeMultipart messageContent) throws MessagingException;
}
