create table order_info(
    order_id varchar(32) not null comment '订单id',
    buyer_id int(11) not null default 0 comment '买家id',
    product_id int(11) not null default 0 comment '商品id',
    product_price decimal(8, 2) not null default 0 comment '商品当时单价',
    product_quantity int(11) not null default 0 comment '商品数量',
    order_amount decimal(8, 2) not null comment '订单总金额',
    order_status tinyint(3) not null default '0' comment '订单状态，默认为新下单',
    pay_status tinyint(3) not null default '0' comment '支付状态，默认为未支付',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (order_id)
) comment '订单详情表(暂时实现为一个订单只能够买一种商品)';