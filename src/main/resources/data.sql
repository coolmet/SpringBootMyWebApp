INSERT INTO DB_USERS VALUES('admin','{noop}admin',TRUE,default,'admin firstname-1','admin ŞşĞğÖöÇçİıÜü','user@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('admin1','{noop}admin1',TRUE,default,'admin firstname-1','admin lastname-1','user1@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('admin2','{noop}admin2',FALSE,default,'admin firstname-2','admin lastname-2','user2@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('admin3','{bcrypt}$2y$12$TBeoXLpgznrmxfivbl6N3.aZUPfG7.D2dVga8ir9wbEFAVFTxeo/.',TRUE,default,'admin firstname-2','admin lastname-2','user4@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('user1','{bcrypt}$2a$10$0nz0StCmfnC9OGRSM0t7xOejjPO.Ytl2JzFUxp8HYubBAYtGUEAQK',TRUE,default,'user firstname-1','user lastname-1','user5@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('user2','{bcrypt}$2a$10$3s0coUAes3oOuAeVI2FuGOuP8aUaWOhU7/5zCgN/u0nBEgikR7fQK',TRUE,default,'user firstname-2','user lastname-2','user6@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('user3','{bcrypt}$2a$10$BZC6iAMpHGd3W5pM7o4uUusMAlxVk6F55U6eeVjlMNkOR1BRX8RrW',TRUE,default,'user firstname-3','user lastname-3','user7@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');
INSERT INTO DB_USERS VALUES('user4','{noop}secret',TRUE,default,'user firstname-4','user lastname-4','user8@email.com','693a0379-e9a3-49bc-9778-5db08667a6c6','2018-05-27 10:00:04');

INSERT INTO DB_AUTHORITIES VALUES(default,'admin','ROLE_ADMIN');
INSERT INTO DB_AUTHORITIES VALUES(default,'admin1','ROLE_ADMIN');
INSERT INTO DB_AUTHORITIES VALUES(default,'admin2','ROLE_ADMIN');
INSERT INTO DB_AUTHORITIES VALUES(default,'admin3','ROLE_ADMIN');
INSERT INTO DB_AUTHORITIES VALUES(default,'user1','ROLE_USER');
INSERT INTO DB_AUTHORITIES VALUES(default,'user2','ROLE_USER');
INSERT INTO DB_AUTHORITIES VALUES(default,'user2','ROLE_EDITOR');
INSERT INTO DB_AUTHORITIES VALUES(default,'user3','ROLE_USER');
INSERT INTO DB_AUTHORITIES VALUES(default,'user3','ROLE_EDITOR');
INSERT INTO DB_AUTHORITIES VALUES(default,'user4','ROLE_USER');


INSERT INTO DB_IMAGES VALUES(default,'indexintro','Spring Boot',FILE_READ('./dbimages/SpringBoot.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Register User',FILE_READ('./dbimages/RegisterUser.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Register User 2',FILE_READ('./dbimages/RegisterUser2.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Thymeleaf And JSP Together',FILE_READ('./dbimages/ThymeleafAndJSPTogether.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Bootstrap & Javascript & Jquery',FILE_READ('./dbimages/BootstrapJavascriptJquery.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Thymeleaf Fragments',FILE_READ('./dbimages/ThymeleafFragments.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Google Recaptcha',FILE_READ('./dbimages/GoogleReCaptcha.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Multi Language',FILE_READ('./dbimages/MultiLanguage.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Multi Language 2',FILE_READ('./dbimages/MultiLanguage.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','H2 Database',FILE_READ('./dbimages/H2Database.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Edit User Settings',FILE_READ('./dbimages/EditUserSettings.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Edit Users',FILE_READ('./dbimages/EditUsers.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Restfull Json',FILE_READ('./dbimages/RestfullJson.png'));
INSERT INTO DB_IMAGES VALUES(default,'indexintro','Restfull XML',FILE_READ('./dbimages/RestfullXML.png'));


