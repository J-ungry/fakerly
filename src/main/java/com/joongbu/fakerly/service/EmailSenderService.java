package com.joongbu.fakerly.service;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import org.thymeleaf.context.Context;

@Service
public class EmailSenderService {
	
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

	public void sendEmail(
			String title, 
			String to, 
			String templateName, 
			HashMap<String, String> values
			) throws MessagingException, IOException {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setSubject(title);
				helper.setTo(to);

				Context context = new Context();
				values.forEach((key, value) -> {
					context.setVariable(key, value);
				});

				String html = templateEngine.process(templateName, context);
				helper.setText(html, true);

				javaMailSender.send(message);
			}
}
