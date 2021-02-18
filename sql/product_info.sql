create table product_info(
    product_id int(11) not null auto_increment,
    product_name varchar(64) not null comment '商品名称',
    product_price decimal(8, 2) not null default 0 comment '单价',
    product_sales int(11) not null default 0 comment '销售量',
    product_description varchar(500) comment '描述',
    product_icon varchar(512) comment '小图',
    product_status tinyint(3) default 0 comment '商品状态，0正常1下架',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (product_id)
) comment '商品详情表';