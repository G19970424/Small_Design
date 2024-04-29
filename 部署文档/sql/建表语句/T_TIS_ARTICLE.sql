drop table t_tis_article;
create table t_tis_article(
                           f_id VARCHAR(64) NOT NULL COMMENT '文章id',
                           f_user_id VARCHAR(64) NOT NULL COMMENT '发布用户id',
                           f_title VARCHAR(64) NOT NULL COMMENT '日记标题',
                           f_label VARCHAR(64) NOT NULL COMMENT '日记标签',
                           f_content VARCHAR(255) DEFAULT NULL COMMENT '日记内容',
                           f_heat TINYINT DEFAULT NULL COMMENT '浏览量',
                           f_create_time DATETIME DEFAULT NULL COMMENT '创建时间',
                           primary key(f_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 comment='文章表';