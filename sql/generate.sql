drop database if exists TE2Crud;

create database TE2Crud;

use TE2Crud;

create table Products (
	id int not null,
	name varchar(128) not null,
	img_url varchar(128) not null,
	short_description varchar(256) not null,
	long_description varchar(2048) not null,
	constraint pk_products primary key (id)
);