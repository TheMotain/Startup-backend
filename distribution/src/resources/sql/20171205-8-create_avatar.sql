CREATE TABLE avatar (
	pupil integer primary key,
	avatar_id text not null default 'default',
	constraint avatar_fk foreign key (pupil) references pupil(id) on delete cascade
);