create table buyer_info(
    buyer_id int(11) not null auto_increment,
    username varchar(32) not null,
    gender tinyint(4) not null default 0 COMMENT '1代表男性，2代表女性',
    age int(11) not null default 0,
    telephone varchar(255) not null default '',
    register_mode varchar(255) not null default '' comment 'byphone, bywechat, byalipay',
    third_party_id varchar(64) default '',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (buyer_id),
    unique key telephone_unique_index (telephone)
) comment '买家信息表';