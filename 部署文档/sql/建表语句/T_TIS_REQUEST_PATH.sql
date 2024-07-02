drop table t_tis_request_path;
create table t_tis_request_path
(
    id int auto_increment comment '主键id' primary key,
    url varchar(64)  not null comment '请求路径',
    description varchar(128) null comment '路径描述'
)
comment '请求路径';