package com.carwash.washer.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.carwash.washer.config.MQConfig;
import com.carwash.washer.models.Notification;

@Component
public class NotificationReceiverController {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(Notification message) {
        System.out.println(message);
    }

}
