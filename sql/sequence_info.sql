create table sequence_info(
    name varchar(255) not null,
    current_value int(11) not null default 0,
    step int(11) not null default 0,
    primary key (name)
);