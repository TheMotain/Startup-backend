create table responsable(
	user integer primary key,
	classroom integer primary key,
	constraint user_fk foreign key (user) references user(id),
	constraint classroom_fk foreign key (classroom) references classroom(id)
);
