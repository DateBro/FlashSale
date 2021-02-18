create table product_stock(
    stock_id int(11) not null auto_increment,
    stock int(11) not null default 0,
    product_id int(11) not null default 0,
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (stock_id)
) comment '商品库存表(将库存与商品信息分开)';