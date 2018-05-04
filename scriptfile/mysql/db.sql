DROP DATABASE IF EXISTS personal_item;
CREATE DATABASE personal_item CHARACTER SET utf8;
USE personal_item;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL COMMENT '用户名',
  `logins` int(10) NOT NULL DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ====创建用户并授权====
CREATE USER select_pit IDENTIFIED BY 'abcdef';
GRANT SELECT on personal_item.* to 'pit_select'@'%' IDENTIFIED by 'xxxx';-- 创建并授权
DROP user 'select_pit'@'%';-- 删除用户
SHOW GRANTS for 'select_pit'@'%';-- 查看有哪些权限
ALTER user 'select_pit'@'%' WITH MAX_USER_CONNECTIONS 100;-- 修改最大连接数
GRANT UPDATE on personal_item.* to 'select_pit'@'%';-- 授予权限
REVOKE UPDATE on personal_item.* from 'select_pit'@'%';-- 撤销权限
-- 修改密码
GRANT USAGE on personal_item.* to 'select_pit'@'%'  IDENTIFIED by 'xxxx';

-- 修改密码策略
SHOW VARIABLES LIKE 'validate_password%';
set GLOBAL validate_password_special_char_count=0;

flush tables with read lock;-- 锁表
mysqldump -uroot -p1234 dy_qqopen > dy_qqopen.sql -- 备份整个数据库
unlock tables;-- 释放锁
GRANT ALL PRIVILEGES