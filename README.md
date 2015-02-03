Problem Statement:

Create a web service that provides an abstraction between two different email service providers.

Specifications:

{HOST:PORT}/notifications/email

to - email address to send to - REQUIRED
to_name - name of receiver - REQUIRED
from - email address of sender from - REQUIRED
from_name - name of sender - REQUIRED
subject - subject line of email - REQUIRED
body - body of email - REQUIRED
send_time - send time in simple date format (YYYY-MM-DD HH:MM:SS) – OPTIONAL

Example Request Payloads:

1. Following will send email at 2PM on February 4, 2015:
{“to”: “fake@example.com”,“to_name”: “Ms. Fake”,“from”: “noreply@uber.com”,“from_name”: “Uber”,“subject”: “A Message from Uber”,“body”: “<h1>Your Bill</h1><p>$10</p>”,”send_time”:”2015-02-04 14:00:00”}

2. Following will send email immediately:
{“to”: “fake@example.com”,“to_name”: “Ms. Fake”,“from”: “noreply@uber.com”,“from_name”: “Uber”,“subject”: “A Message from Uber”,“body”: “<h1>Your Bill</h1><p>$10</p>”}

Installation instructions:

1. Download/Clone this git repository. Path to this directory will be referred as {gitroot} from here on in.
2. Edit {gitroot}/installation_files/exports per your MySQL database configuration (This step assumes that you have MySQL database server and client installed. You can download mysql from “http://dev.mysql.com/downloads/mysql/”). Set environment variables by executing command "source {gitroot}/installation_files/exports" or otherwise.
3. Create database schema using {gitroot}/installation_files/uber-notification-schema.sql
4. Populate database values using {gitroot}/installation_files/uber-notification-secrets.sql
5. Run command “mvn clean install” (This step assumes that you have maven installed. You can download maven from “http://maven.apache.org/download.cgi”)
6. Execute command “mvn jetty:run”

Implementation details and tradeoffs:
1. I chose Spring MVC framework to create web services because I am most familiar with Java as a programming language and Spring as a web framework. I was also considering using Django or Rails because I have worked with both the frameworks in the past. But, the requirements document suggests to stay away from those frameworks.
2. Difficulty in unit testing persistence layer is a tradeoff with Spring framework as there is limited out of the box support for something like fixtures that is offered in Rails framework. But, AOP(Aspect Oriented Programming) support in Spring framework is very useful.

Features:
1. If default email provider fails, the service switches to non-default email provider. If all email providers fail, failed notification is logged.
2. Switching default email providers does not need a server restart.
3. I also added an aspect called TimingAspect to log performance of each email service. Over the period of time, those logs can help choose faster email services.
4. All the email notifications are saved in the database.
5. Delayed delivery is supported for Mandrill.

Given more time, I would like to:
1. Add more unit and integration tests.
2. Save failed emails in database instead of just logging those.
3. Host the application using heroku or aws and implement webhooks. Webhook for receiving events from Mandrill could not be tested because I did not have a chance to host the application using heroku or aws. The development and testing was done using localhost.
4. Implement intelligent email provider switching based on number of failures.
