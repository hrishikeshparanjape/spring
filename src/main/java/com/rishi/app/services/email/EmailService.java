package com.rishi.app.services.email;

import com.rishi.app.models.EmailNotification;

public interface EmailService {
	public boolean send(EmailNotification email);
	public boolean scheduleSend();
}
