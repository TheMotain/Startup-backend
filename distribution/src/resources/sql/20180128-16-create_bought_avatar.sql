create table avatar_price(
	id text primary key, 
	price integer
);

create table bougth_avatar(
	pupil integer primary key, 
	avatar_id text, 
	constraint pupil_fk foreign key (pupil) references pupil(id) on delete cascade, 
	constraint avatar_fk foreign key (avatar_id) references avatar_price(id) on delete cascade
);

insert into avatar_price select distinct avatar_id, 0 from avatar;
insert into bougth_avatar select pupil,avatar_id from avatar;

alter table avatar add constraint avatar_bougth_pupil_fk foreign key (pupil) references bougth_avatar(pupil);
