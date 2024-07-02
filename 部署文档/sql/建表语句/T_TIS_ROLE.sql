drop table t_tis_role;
create table t_tis_role(
    id int auto_increment comment '主键id' primary key,
    role_name varchar(32) null comment '角色名',
    role_description varchar(64) null comment '角色说明'
)
comment '用户角色表';