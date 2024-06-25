drop table t_tis_user;
create table t_tis_user(
    f_id int NOT NULL AUTO_INCREMENT COMMENT '用户id',
    f_user_name VARCHAR(64) NOT NULL COMMENT '昵称',
    f_login_user_name VARCHAR(64) NOT NULL COMMENT '用户登录名称',
    f_password VARCHAR(64) NOT NULL COMMENT '密码',
    f_user_status CHAR(1) DEFAULT '0' COMMENT '账号状态（0正常，1注销）',
    f_age TINYINT DEFAULT NULL COMMENT '年龄',
    f_email VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
    f_phone_number VARCHAR(32) DEFAULT NULL COMMENT '电话号码',
    f_sex CHAR(1) DEFAULT NULL COMMENT '性别（0男，1女，2未知）',
    f_user_type CHAR(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
    f_create_time DATETIME DEFAULT NULL COMMENT '创建时间',
    primary key(f_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment='用户表';

insert into  t_tis_user(F_LOGIN_USER_NAME,F_USER_NAME,F_PASSWORD,F_USER_TYPE)  values('gejiaju','gejiaju','$2a$10$oA0vUapinngy0Fv7TlXg/e9QnOV2GqkTgJ7e/2jDTX2qNuOUNmOeO','1' );