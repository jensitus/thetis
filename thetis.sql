drop table if exists reader;

drop table if exists post;
drop table if exists user;


create table user(
  id int not null auto_increment,
  username varchar(40) not null unique,
  password varchar(255) not null,
  description varchar(400),
  primary key (id)
);

create table post(
  id int not null auto_increment,
  title varchar(100),
  body varchar(10000) not null,
  userId int not null,
  primary key(id)
);

create table reader(
  id int not null auto_increment,
  readerId int,
  toReadId int,
  primary key(id)
);
