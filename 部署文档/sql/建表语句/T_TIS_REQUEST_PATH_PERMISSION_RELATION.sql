drop table t_tis_request_path_permission_relation;
create table t_tis_request_path_permission_relation
(
    id int null comment '主键id',
    url_id int null comment '请求路径id',
    permission_id int null comment '权限id'
)
comment '路径权限关联表';