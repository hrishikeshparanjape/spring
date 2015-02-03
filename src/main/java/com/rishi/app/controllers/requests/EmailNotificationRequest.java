package com.rishi.app.controllers.requests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rishi.app.models.EmailNotification;

public class EmailNotificationRequest extends EmailNotification {
	
    private static final Logger log = LoggerFactory.getLogger(EmailNotificationRequest.class);
    
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	
	private String sendAt;

	@JsonProperty("to_name")
	public String getToName() {
		return super.getToName();
	}

	@JsonProperty("to_name")
	public void setToName(String toName) {
		super.setToName(toName);
	}

	@JsonProperty("to")
	public String getToEmail() {
		return super.getToEmail();
	}

	@JsonProperty("to")
	public void setToEmail(String toEmail) {
		super.setToEmail(toEmail);
	}
	
	@JsonProperty("from")
	public String getFromEmail() {
		return super.getFromEmail();
	}

	@JsonProperty("from")
	public void setFromEmail(String fromEmail) {
		super.setFromEmail(fromEmail);
	}

	
	@JsonProperty("from_name")
	public String getFromName() {
		return super.getFromName();
	}

	@JsonProperty("from_name")
	public void setFromName(String fromName) {
		super.setFromName(fromName);
	}
	
	@JsonProperty("send_time")
    public String getSendAt() {
	    return this.sendAt;
    }

	@JsonProperty("send_time")
    public void setSendAt(String sendAt) {
	    this.sendAt=sendAt;
	    
	    try {
	        Calendar cal  = Calendar.getInstance();
            cal.setTime((new SimpleDateFormat("YYYY-MM-DD HH:MM:SS")).parse(sendAt));
            super.setSendTime(cal);
        } catch (ParseException e) {
            log.warn("Unable to parse date string:",sendAt);
        }
    }
	
	
	public boolean isValid () {
		
		// First validate not null
		
		if (getToEmail() == null 
				|| getToName() == null 
				|| getFromEmail() == null
				|| getFromName() == null 
				|| getSubject() == null
				|| getBody() == null) {
			return false;	
		}
		
		// Second validate email address
		
		if (!pattern.matcher(getToEmail()).matches() || !pattern.matcher(getFromEmail()).matches()) {
			return false;
		}
		
		// Third each field has to be at least 2 characters long
		if (getToEmail().length() < 2 
				|| getToName().length() < 2 
				|| getFromEmail().length() < 2
				|| getFromName().length() < 2 
				|| getSubject().length() < 2
				|| getBody().length() < 2) {
			return false;	
		}

		// If everything is okay, then we can say that the request is valid
		return true;
	}
	
}
