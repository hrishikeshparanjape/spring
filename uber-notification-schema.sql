CREATE TABLE `ConfigurationProperty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `EmailNotification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `fromEmail` varchar(255) DEFAULT NULL,
  `fromName` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `toEmail` varchar(255) DEFAULT NULL,
  `toName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
