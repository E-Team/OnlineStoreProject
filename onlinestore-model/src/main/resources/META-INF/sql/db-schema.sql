
drop table product if exists

create table product (
	id bigint generated by default as identity (start with 1), 
	price double not null, 
	product_name varchar(100) not null, 
	primary key (id))