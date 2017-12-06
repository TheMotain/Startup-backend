create table qcm(
	id serial primary key,
	title text not null,
	instruction text,
	classroom integer,
	date_ins date default CURRENT_DATE,
	constraint classroom_fk foreign key (classroom) references classroom(id)
);

create table question(
	id serial primary key,
	query text not null,
	qcm integer,
	nb_points integer,
	constraint qcm_fk foreign key(qcm) references qcm(id)
);

create table answer(
	id serial primary key, 
	choice text not null,
	good boolean,
	question integer,
	constraint question_fk foreign key(question) references question(id)
);