create table buyer_password (
    id int(11) not null auto_increment,
    encrypt_password varchar(128) not null default '',
    buyer_id int(11) not null default 0,
    primary key (id)
) comment '买家密码表';