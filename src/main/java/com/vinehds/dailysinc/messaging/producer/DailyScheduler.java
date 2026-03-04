package com.vinehds.dailysinc.messaging.producer;

import com.vinehds.dailysinc.config.RabbitMQConfig;
import com.vinehds.dailysinc.controller.dto.EmailDTO;
import com.vinehds.dailysinc.messaging.event.DailyEmailRequestedEvent;
import com.vinehds.dailysinc.model.entities.Email;
import com.vinehds.dailysinc.model.enums.StatusEmail;
import com.vinehds.dailysinc.service.EmailService;
import com.vinehds.dailysinc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DailyScheduler {

    @Value("${spring.mail.username}")
    private String usernameDefault;

    private final RabbitTemplate rabbitTemplate;
    private final EmailService emailService;
    private final UserService userService;

    @Scheduled(cron = "0 57 1 * * ?", zone = "America/Sao_Paulo")
    public void sendDailyRequests() {

        System.out.println("Enviando emails diários para os desenvolvedores ativos...");

        var devs = userService.findAllUsersActive();
        devs.forEach(dev -> {
            Email email = new Email();
            email.setOwnerRef("Default Owner");
            email.setEmailFrom(usernameDefault);
            email.setSubject("Assunto teste");
            email.setText("Corpo do email teste");
            email.setSendDateEmail(LocalDateTime.now());
            email.setStatusEmail(StatusEmail.PROCESSING);
            email.setEmailTo(dev.getEmail());

            Email emailCreated = emailService.saveEmail(email);

            var dto = new EmailDTO(
                    usernameDefault,
                    email.getEmailFrom(),
                    email.getEmailTo(),
                    email.getSubject(),
                    email.getText()
            );

            var event = new DailyEmailRequestedEvent(dev.getId(), emailCreated.getEmailId(), dto);

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.DAILY_EXCHANGE,
                    RabbitMQConfig.DAILY_ROUTING_KEY,
                    event
            );
        });
    }
}
