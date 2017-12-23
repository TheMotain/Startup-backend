create table role_user(
	id serial primary key,
	role text
);

insert into role_user (role) values ('TEACHER');

create table users(
	id serial primary key,
	uuid TEXT NOT NULL UNIQUE DEFAULT uuid_generate_v4(),
	name text not null,
	firstname text,
	email text not null,
	password text not null,
	role integer not null,
	constraint role_fk foreign key (role) references role_user(id)
);