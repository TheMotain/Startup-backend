create table pupil (
	id serial primary key,
	firstname text not null,
	lastname text not null,
	borndate date not null,
	classroom integer,
	constraint classroom_fk foreign key (classroom) references classroom(id)
);
