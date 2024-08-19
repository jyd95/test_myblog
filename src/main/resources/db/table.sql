create table post(
	id int auto_increment primary key,
    title varchar(20) not null,
    content varchar(20) default ' ',
    writer varchar(20) default 'ㅇㅇ',
    password varchar(20) default '1234'
);