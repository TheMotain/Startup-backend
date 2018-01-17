create table responsable(
	user_id integer,
	classroom_id integer,
	constraint responsable_pk primary key (user_id,classroom_id),
	constraint user_fk foreign key (user_id) references users(id),
	constraint classroom_fk foreign key (classroom_id) references classroom(id)
);
