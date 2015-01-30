package com.rishi.app.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rishi.app.controllers.requests.EmailNotificationRequest;
import com.rishi.app.models.Notification;
import com.rishi.app.services.NotificationService;

@Controller
public class NotificationController {
	
	private static final Logger log = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value = "/notifications/email", method = RequestMethod.POST)
	public void sendEmail(HttpServletRequest request, HttpServletResponse response, @RequestBody EmailNotificationRequest emailRequest) throws IOException {
		try {
			if(emailRequest.isValid()) {
				String plainTextBody = Jsoup.parse(emailRequest.getBody()).text();
				notificationService.sendEmail(emailRequest);
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}
	
}
