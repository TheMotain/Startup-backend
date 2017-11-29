create table point (
	bonus bigint,
	malus bigint,
	pupil integer primary key,
	constraint pupil_fk foreign key (pupil) references pupil(id)
);
