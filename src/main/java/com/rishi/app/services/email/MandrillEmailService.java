package com.rishi.app.services.email;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.app.models.ConfigurationProperty;
import com.rishi.app.models.EmailNotification;
import com.rishi.app.repositories.ConfigurationPropertyRepository;
import com.rishi.app.thirdparty.mandrill.json.MandrillMessagePayload;
import com.rishi.app.thirdparty.mandrill.json.Message;
import com.rishi.app.thirdparty.mandrill.json.To;

@Service
public class MandrillEmailService implements EmailService {

	private static final Logger log = LoggerFactory
			.getLogger(MandrillEmailService.class);

	private static final String MANDRILL_API_KEY = "mandrill.api.key";

	@Autowired
	private ConfigurationPropertyRepository config;

	@Override
	public boolean send(EmailNotification email) {
		//ConfigurationProperty mandrillKey = config
			//	.findConfigurationPropertyByName(MANDRILL_API_KEY);
		//mandrillKey.getValue();

		log.info("Sending email through Mandrill service");
		boolean ret = false;
		HttpClient httpClient = new DefaultHttpClient();
		
		try {
			HttpPost request = new HttpPost("https://mandrillapp.com/api/1.0/messages/send.json");
			StringEntity params = new StringEntity(buildMandrillJsonPayload(email));
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			int responseStatus = response.getStatusLine().getStatusCode();

			if (responseStatus == 200) {
				ret = true;
			}
		} catch (Exception ex) {
			log.error("Error sending email through Mandrill service:", ex);
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

	private String buildMandrillJsonPayload(EmailNotification email)
			throws JsonGenerationException, JsonMappingException, IOException {
		// create recipients
		To[] recipients = new To[1];
		To recipient = new To();
		recipient.setEmail(email.getToEmail());
		recipient.setName(email.getToName());
		recipients[0] = recipient;
		// create message
		Message message = new Message();
		message.setFromEmail(email.getFromEmail());
		message.setFromName(email.getFromName());
		message.setSubject(email.getSubject());
		message.setText(email.getBody());
		message.setTo(recipients);
		// create payload
		MandrillMessagePayload payload = new MandrillMessagePayload();
		payload.setAsync("false");
		payload.setKey("2EzrPU5vxHxYJVQ0jLosoA");
		payload.setMessage(message);
		// convert payload to string
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(payload);
	}

}
