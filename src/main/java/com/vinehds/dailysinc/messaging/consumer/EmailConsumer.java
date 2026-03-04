package com.vinehds.dailysinc.messaging.consumer;

import com.vinehds.dailysinc.messaging.event.DailyEmailRequestedEvent;
import com.vinehds.dailysinc.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

     private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload DailyEmailRequestedEvent event) {
        emailService.sendDailyEmail(event.developerId(), event.email(), event.emailId());
    }


}
