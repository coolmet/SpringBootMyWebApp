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


