package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;


@Controller
@Service
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void listenMail() {

        @SuppressWarnings("resource")
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
                "/gmail-imap-idle-config.xml");
        DirectChannel inputChannel = ac.getBean("receiveChannel", DirectChannel.class);
        inputChannel.subscribe(new MessageHandler() {

            @Override
            public void handleMessage(org.springframework.messaging.Message<?> message) throws org.springframework.messaging.MessagingException {
                javax.mail.Message message2 = (Message) message.getPayload();

                try {
                    logger.warn("Message: " + ((Message) message.getPayload()).getContent().toString());

                    logger.warn("Subject: " + ((Message) message.getPayload()).getSubject());

                    logger.warn("From: " + ((InternetAddress)((Address)(((Message)message.getPayload()).getFrom()[0]))).getAddress());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
