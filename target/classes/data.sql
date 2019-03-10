INSERT INTO USERS VALUES('admin','{noop}admin',TRUE,default,'admin firstname-1','admin lastname-1','admin email-1','');
INSERT INTO USERS VALUES('admin1','{noop}admin1',TRUE,default,'admin firstname-1','admin lastname-1','admin email-1','');
INSERT INTO USERS VALUES('admin2','{noop}admin2',TRUE,default,'admin firstname-2','admin lastname-2','admin email-2','');
INSERT INTO USERS VALUES('admin3','{bcrypt}$2y$12$TBeoXLpgznrmxfivbl6N3.aZUPfG7.D2dVga8ir9wbEFAVFTxeo/.',TRUE,default,'admin firstname-2','admin lastname-2','admin email-2','');
INSERT INTO USERS VALUES('user1','{bcrypt}$2a$10$0nz0StCmfnC9OGRSM0t7xOejjPO.Ytl2JzFUxp8HYubBAYtGUEAQK',TRUE,default,'user firstname-1','user lastname-1','user email-1','');
INSERT INTO USERS VALUES('user2','{bcrypt}$2a$10$3s0coUAes3oOuAeVI2FuGOuP8aUaWOhU7/5zCgN/u0nBEgikR7fQK',TRUE,default,'user firstname-2','user lastname-2','user email-2','');
INSERT INTO USERS VALUES('user3','{bcrypt}$2a$10$BZC6iAMpHGd3W5pM7o4uUusMAlxVk6F55U6eeVjlMNkOR1BRX8RrW',TRUE,default,'user firstname-3','user lastname-3','user email-3','');
INSERT INTO USERS VALUES('user4','{noop}secret',TRUE,default,'user firstname-4','user lastname-4','user email-4','');

INSERT INTO AUTHORITIES VALUES('admin','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES('admin1','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES('admin2','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES('admin3','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES('user1','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user2','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user2','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES('user4','ROLE_USER');


