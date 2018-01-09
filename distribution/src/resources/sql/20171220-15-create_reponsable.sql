create table responsable(
	user_id integer primary key,
	classroom_id integer primary key,
	constraint user_fk foreign key (user_id) references users(id),
	constraint classroom_fk foreign key (classroom_id) references classroom(id)
);
