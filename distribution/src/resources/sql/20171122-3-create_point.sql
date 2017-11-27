create table point (
	bonus bigint,
	malus bigint,
	pupil integer unique,
	constraint pupil_fk foreign key (pupil) references pupil(id)
);