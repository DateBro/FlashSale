create table promo_info(
    promo_id int(11) not null auto_increment comment '活动id',
    promo_name varchar(255) not null default '' comment '活动名称',
    product_id int(11) not null default 0 comment '商品id',
    promo_product_price decimal(8, 2) not null default 0 comment '活动时商品单价',
    promo_start_time timestamp not null default current_timestamp comment '活动开始时间',
    promo_end_time timestamp not null default current_timestamp comment '活动结束时间',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (promo_id)
) comment '活动详情表';