create table point (
	id serial primary key,
	bonus bigint,
	malus bigint,
	pupil integer,
	constraint pupil_fk foreign key (pupil) references pupil(id)
);