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
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.app.annotations.Timed;
import com.rishi.app.models.ConfigurationProperty;
import com.rishi.app.models.EmailNotification;
import com.rishi.app.repositories.ConfigurationPropertyRepository;

@Service
public class SendGridEmailService implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(MandrillEmailService.class);

    private static final String SENDGRID_API_USER = "sendgrid.api.user";
    private static final String SENDGRID_API_KEY = "sendgrid.api.key";
    
    @Override
    public String getName(){
        return "sendgrid";
    }

    @Autowired
    private ConfigurationPropertyRepository config;

    @Override
    @Timed
    public boolean send(EmailNotification email) {
        log.info("Sending email through SendGrid service");
        ConfigurationProperty sendgridUser = config.findConfigurationPropertyByName(SENDGRID_API_USER);
        ConfigurationProperty sendgridKey = config.findConfigurationPropertyByName(SENDGRID_API_KEY);
        boolean ret = false;

        if (sendgridUser != null && !sendgridUser.getValue().isEmpty() && sendgridKey != null && !sendgridKey.getValue().isEmpty()) {
            HttpClient httpClient = new DefaultHttpClient();
            try {
                HttpPost request = new HttpPost("https://api.sendgrid.com/api/mail.send.json");
                request.setEntity(new UrlEncodedFormEntity(buildSendGridParams(email, sendgridUser.getValue(), sendgridKey.getValue())));
                HttpResponse response = httpClient.execute(request);
                int responseStatus = response.getStatusLine().getStatusCode();
                if (responseStatus == 200) {
                    ret = true;
                } else {
                    byte[] responseBody = new byte[1000];
                    response.getEntity().getContent().read(responseBody);
                    log.error("Error sending email through Mandrill service:", new String(responseBody));
                }
            } catch (Exception ex) {
                log.error("Error sending email through SendGrid service:", ex);
            } finally {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return ret;
    }

    @Override
    public boolean scheduleSend() {
        // TODO Auto-generated method stub
        return false;
    }

    private List<NameValuePair> buildSendGridParams(EmailNotification email, String user, String key) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(8);
        nameValuePairs.add(new BasicNameValuePair("api_user", user));
        nameValuePairs.add(new BasicNameValuePair("api_key", key));
        nameValuePairs.add(new BasicNameValuePair("to", email.getToEmail()));
        nameValuePairs.add(new BasicNameValuePair("toname", email.getToName()));
        nameValuePairs.add(new BasicNameValuePair("from", email.getFromEmail()));
        nameValuePairs.add(new BasicNameValuePair("fromname", email.getFromName()));
        nameValuePairs.add(new BasicNameValuePair("subject", email.getSubject()));
        String plainTextBody = Jsoup.parse(email.getBody()).text();
        nameValuePairs.add(new BasicNameValuePair("text", plainTextBody));
        return nameValuePairs;

    }

}
