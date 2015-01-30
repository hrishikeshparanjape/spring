package com.rishi.app.services.email;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rishi.app.models.EmailNotification;

@Service
public class SendGridEmailService implements EmailService {

	private static final Logger log = LoggerFactory.getLogger(MandrillEmailService.class);
	
	@Override
	public boolean send(EmailNotification email) {
		log.info("Sending email through SendGrid service");
		boolean ret = false;
		HttpClient httpClient = new DefaultHttpClient();
	    try {
	        HttpPost request = new HttpPost("https://api.sendgrid.com/api/mail.send.json");
	        request.setEntity(new UrlEncodedFormEntity(buildSendGridParams(email)));
	        HttpResponse response = httpClient.execute(request);
	        int responseStatus = response.getStatusLine().getStatusCode();
	        if(responseStatus == 200) {
	        	ret = true;
	        } else {
		        byte[] responseBody = new byte[1000];
		        response.getEntity().getContent().read(responseBody);
	        	log.error("Error sending email through Mandrill service:", new String(responseBody));
	        }
	    }catch (Exception ex) {
	    	log.error("Error sending email through SendGrid service:", ex);
	    } finally {
	        httpClient.getConnectionManager().shutdown();
	    }
		return ret;
	}

	@Override
	public boolean scheduleSend() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private List<NameValuePair> buildSendGridParams(EmailNotification email) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(8);
        nameValuePairs.add(new BasicNameValuePair("api_user", "hrishikeshp19"));
        nameValuePairs.add(new BasicNameValuePair("api_key", "Password2"));
        nameValuePairs.add(new BasicNameValuePair("to", email.getToEmail()));
        nameValuePairs.add(new BasicNameValuePair("toname", email.getToName()));
        nameValuePairs.add(new BasicNameValuePair("from", email.getFromEmail()));
        nameValuePairs.add(new BasicNameValuePair("fromname", email.getFromName()));
        nameValuePairs.add(new BasicNameValuePair("subject", email.getSubject()));
        nameValuePairs.add(new BasicNameValuePair("text", email.getBody()));
        return nameValuePairs;
        

	}

}
