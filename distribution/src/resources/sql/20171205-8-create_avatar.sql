CREATE TABLE avatar (
	pupil integer primary key,
	avatar_name text not null,
	avatar_id text not null
	constraint pupil_fk foreign key (pupil) references pupil(id)
);