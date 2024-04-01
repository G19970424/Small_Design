drop table t_tis_user;
create table t_tis_user(
    f_id VARCHAR() NOT NULL COMMENT '用户id',
    f_user_name VARCHAR() NOT NULL COMMENT '昵称',
    f_login_user_name VARCHAR() NOT NULL COMMENT '用户登录名称',
    f_password VARCHAR() NOT NULL COMMENT '密码',
    f_user_status CHAR() DEFAULT '0' COMMENT '账号状态（0正常，1注销）',
    f_age TINYINT DEFAULT NULL COMMENT '年龄',
    f_email VARCHAR() DEFAULT NULL COMMENT '邮箱',
    f_phone_number VARCHAR() DEFAULT NULL COMMENT '电话号码',
    f_sex CHAR() DEFAULT NULL COMMENT '性别（0男，1女，2未知）',
    f_user_type CHAR() NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
    f_create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    primary key('f_id')
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment='用户表'