delete from user where name = 'test';
insert into user(name, password, created_time) values ('test', '123', now());