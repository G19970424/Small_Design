drop table t_tis_role_permission_relation;
create table t_tis_role_permission_relation
(
    id int auto_increment comment '主键id' primary key,
    role_id int null comment '角色id',
    permission_id int null comment '权限id'
)
comment '角色-权限关联关系表';