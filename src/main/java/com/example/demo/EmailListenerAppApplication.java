package com.example.demo;

import com.example.demo.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.*;
import org.springframework.integration.channel.DirectChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SpringBootApplication
public class EmailListenerAppApplication {

	@Autowired
	EmailService emailService;

	private static Log logger = LogFactory.getLog(EmailListenerAppApplication.class);

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(EmailListenerAppApplication.class, args);
		EmailService emailService = applicationContext.getBean(EmailService.class);
		emailService.listenMail();
	}

}
