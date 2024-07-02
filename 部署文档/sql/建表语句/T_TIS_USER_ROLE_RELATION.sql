drop table t_tis_user_role_relation;
create table t_tis_user_role_relation
(
    id int auto_increment comment '主键id' primary key,
    user_id int null comment '用户id',
    role_id int null comment '角色id'
)
comment '用户角色关联关系表';