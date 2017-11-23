create table qcm(
	id serial primary key,
	title text not null,
	instruction text,
	classroom integer,
	constraint classroom_fk foreign key (classroom) references classroom(id)
);

create table question(
	id serial primary key,
	query text not null,
	qcm integer,
	constraint qcm_fk foreign key(qcm) references qcm(id)
);

create table answer(
	id serial primary key, 
	choice text not null,
	good boolean,
	question integer,
	constraint question_fk foreign key(question) references question(id)
);