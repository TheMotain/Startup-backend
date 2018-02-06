CREATE TABLE avatar (
	pupil integer primary key,
	avatar_id text not null default 'avatar1',
	constraint avatar_fk foreign key (pupil) references pupil(id) on delete cascade
);