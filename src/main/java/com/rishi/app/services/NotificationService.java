package com.rishi.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.app.models.ConfigurationProperty;
import com.rishi.app.models.EmailNotification;
import com.rishi.app.models.Notification;
import com.rishi.app.repositories.ConfigurationPropertyRepository;
import com.rishi.app.repositories.EmailNotificationRepository;
import com.rishi.app.services.email.EmailService;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private static final String DEFAULT_EMAIL_SERVICE = "default.email.service";

    @Autowired
    private ConfigurationPropertyRepository config;

    @Autowired
    private EmailNotificationRepository emailNotifications;

    @Autowired
    private List<EmailService> emailServices;

    public void sendEmail(EmailNotification email) {
        boolean success = false;
        EmailService defaultEmailService = getDefaultEmailService();
        if (defaultEmailService != null && defaultEmailService.send(email)) {
            success = true;
            email.setProvider(defaultEmailService.getName());
            saveNotification(email);
        } else {
            List<EmailService> nonDefaultEmailServices = getNonDefaultEmailServices();
            for (EmailService emailService : nonDefaultEmailServices) {
                if (emailService.send(email)) {
                    success = true;
                    email.setProvider(emailService.getName());
                    saveNotification(email);
                    break;
                }
            }
        }

        if (!success) {
            log.error("Failed to send notification", email);
        }
    }

    private EmailService getDefaultEmailService() {
        ConfigurationProperty defaultServiceName = config.findConfigurationPropertyByName(DEFAULT_EMAIL_SERVICE);
        if (defaultServiceName != null && !defaultServiceName.getValue().isEmpty()) {
            for (EmailService emailService : emailServices) {
                if (emailService.getName().equals(defaultServiceName.getValue())) {
                    return emailService;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    private List<EmailService> getNonDefaultEmailServices() {
        ConfigurationProperty defaultServiceName = config.findConfigurationPropertyByName(DEFAULT_EMAIL_SERVICE);
        if (defaultServiceName != null && !defaultServiceName.getValue().isEmpty()) {
            List<EmailService> ret = new ArrayList<EmailService>();
            for (EmailService emailService : emailServices) {
                if (!emailService.getName().equals(defaultServiceName.getValue())) {
                    ret.add(emailService);
                }
            }
            return ret;
        } else {
            return emailServices;
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
            email.setSendTime(((EmailNotification) notification).getSendTime());
            email.setCreateDate(Calendar.getInstance());
            email.setLastModified(Calendar.getInstance());
            emailNotifications.create(email);
        }
    }

}
