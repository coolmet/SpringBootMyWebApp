INSERT INTO USERS VALUES(default,'admin','{noop}admin1','admin firstname','admin lastname','admin email','',TRUE);
INSERT INTO USERS VALUES(default,'user1','{bcrypt}$2a$10$0nz0StCmfnC9OGRSM0t7xOejjPO.Ytl2JzFUxp8HYubBAYtGUEAQK','user firstname-1','user lastname-1','user email-1','',TRUE);
INSERT INTO USERS VALUES(default,'user2','{bcrypt}$2a$10$3s0coUAes3oOuAeVI2FuGOuP8aUaWOhU7/5zCgN/u0nBEgikR7fQK','user firstname-2','user lastname-2','user email-2','',TRUE);
INSERT INTO USERS VALUES(default,'user3','{bcrypt}$2a$10$BZC6iAMpHGd3W5pM7o4uUusMAlxVk6F55U6eeVjlMNkOR1BRX8RrW','user firstname-3','user lastname-3','user email-3','',TRUE);
INSERT INTO USERS VALUES(default,'user4','{noop}secret','user firstname-4','user lastname-4','user email-4','',TRUE);


INSERT INTO AUTHORITIES VALUES('admin','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES('user1','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user2','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user2','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES('user4','ROLE_USER');


