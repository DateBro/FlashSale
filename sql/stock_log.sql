create table stock_log(
    stock_log_id varchar(64) not null comment '库存日志id',
    product_id int(11) not null default 0 comment '商品id',
    quantity int(11) not null default 0 comment '这次操作的库存数量',
    status int(11) not null default 0 comment '1表示初始状态，2表示下单扣减库存成功，3表示下单回滚',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (stock_log_id)
) comment '商品库存日志表';