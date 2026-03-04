package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.controller.dto.EmailDTO;
import com.vinehds.dailysinc.model.entities.Email;
import com.vinehds.dailysinc.model.enums.StatusEmail;
import com.vinehds.dailysinc.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender emailSender;


    @Transactional
    public void sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.saveAndFlush(email);
        }
    }

    @Transactional
    public void sendDailyEmail(Long developerId, EmailDTO emailDTO, Long emailId) {
        System.out.println("Enviando email para o desenvolvedor com ID: " + developerId);
        var email = emailRepository.findById(emailId)
                .orElseThrow(() -> new RuntimeException("Email não encontrado com ID: " + emailId));

        sendEmail(email);
    }

    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }

}
