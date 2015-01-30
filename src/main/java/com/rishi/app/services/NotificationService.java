package com.rishi.app.services;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.app.models.EmailNotification;
import com.rishi.app.models.Notification;
import com.rishi.app.repositories.EmailNotificationRepository;
import com.rishi.app.services.email.EmailService;

@Service
public class NotificationService {

	private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	private EmailNotificationRepository emailNotifications;
	
	@Autowired
	private List<EmailService> emailServices;
	
	public void sendEmail(EmailNotification email) {
		boolean success = false;
		for (EmailService emailService : emailServices) {
			if(emailService.send(email)) {
				success=true;
				email.setProvider(emailService.getClass().toString());
				saveNotification(email);
				//break;
			}
		}
		if(!success) {
			log.error("No good");
		}
	}

	private void saveNotification(Notification notification) {
		if (notification instanceof EmailNotification) {
			EmailNotification email = new EmailNotification();
			email.setBody(((EmailNotification) notification).getBody());
			email.setSubject(((EmailNotification) notification).getSubject());
			email.setToEmail(((EmailNotification) notification).getToEmail());
			email.setToName(((EmailNotification) notification).getToName());
			email.setFromEmail(((EmailNotification) notification).getFromEmail());
			email.setFromName(((EmailNotification) notification).getFromName());
			email.setProvider(((EmailNotification) notification).getProvider());
			email.setCreateDate(Calendar.getInstance());
			email.setLastModified(Calendar.getInstance());
			emailNotifications.create(email);
		}
	}

}
