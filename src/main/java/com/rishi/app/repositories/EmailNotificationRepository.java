package com.rishi.app.repositories;

import com.rishi.app.models.EmailNotification;

public interface EmailNotificationRepository {

	EmailNotification create(EmailNotification p);
	EmailNotification update(EmailNotification p);
	EmailNotification delete(Long id);
	EmailNotification find(Long id);
}

