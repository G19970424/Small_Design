drop table t_tis_permission;
create table t_tis_permission
(
    id int auto_increment comment '主键id' primary key,
    permission_code varchar(32) null comment '权限code',
    permission_name varchar(32) null comment '权限名'
)
comment '权限表';