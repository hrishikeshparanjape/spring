package com.rishi.app.models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
public class EmailNotification extends Notification {
    @Column
    private Calendar sendTime;

	@Column
	private String provider;
	
	@Column
	private String toEmail;

	@Column
	private String fromEmail;
	
	@Column
	private String toName;
	
	@Column
	private String fromName;
	
	@Column
	private String subject;
	
	@Column
	private String body;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String to) {
		this.toEmail = to;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String from) {
		this.fromEmail = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }

}
