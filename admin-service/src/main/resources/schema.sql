create schema if not exists secure_admin;
use secure_admin;

create table if not exists users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create table if not exists authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username));
	create unique index ix_auth_username on authorities (username,authority
);

insert into users (username, password, enabled) values ('employeeUser', '$2a$10$KxTc8SYbIB/IaXCWz6NA4ug1pkAYM/e.P.0YQFGE3Ua4FZ6Qf842a', true);
insert into users (username, password, enabled) values ('managerUser', '$2a$10$QPnaeWBWz1BdDglni2CLzO2YMeifVXtQDPgUOVNETTcj8cEGwqiym', true);
insert into users (username, password, enabled) values ('adminUser', '$2a$10$Hc878CPLJ4hOtwyzt6V7..LHtzhcR3zqcXOAPseY9QGg05ZxcsTR6', true);
insert into users (username, password, enabled) values ('leadUser','$2a$10$2lUV/9T8yTHpRGPaHPkV3emBBWBCOv0UIAnup.E3.de9Zs11Sg4A2', true);

insert into authorities (username, authority) values ('employeeUser', 'ROLE_EMPLOYEE');

insert into authorities (username, authority) values ('leadUser', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('leadUser', 'ROLE_LEAD');

insert into authorities (username, authority) values ('managerUser', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('managerUser', 'ROLE_LEAD');
insert into authorities (username, authority) values ('managerUser', 'ROLE_MANAGER');

insert into authorities (username, authority) values ('adminUser', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('adminUser', 'ROLE_LEAD');
insert into authorities (username, authority) values ('adminUser', 'ROLE_MANAGER');
insert into authorities (username, authority) values ('adminUser', 'ROLE_ADMIN');