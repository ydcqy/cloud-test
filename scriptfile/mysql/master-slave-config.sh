#!/usr/bin/bash

#主配置
server_id=11;
log_bin=/var/lib/mysql/mysql-bin;
binlog_format=mixed
binlog_do_db=personal_item
sed -i '/server-id/d' /etc/my.cnf
sed -i '/log-bi/d' /etc/my.cnf
sed -i '/binlog_format/d' /etc/my.cnf
sed -i '/binlog_do_db/d' /etc/my.cnf
sed -i "/\[mysqld\]/a server-id=${server_id}" /etc/my.cnf
sed -i "/\[mysqld\]/a log-bin=${log_bin}" /etc/my.cnf
sed -i "/\[mysqld\]/a binlog_format=${binlog_format}" /etc/my.cnf
sed -i "/\[mysqld\]/a binlog-do-db=${binlog_do_db}" /etc/my.cnf # 多个数据库需要配置多行
mysqldump -uroot -p1234 dy_qqopen > dy_qqopen.sql

#从配置
server_id=12;
relay_log=/var/lib/mysql/t12-relay-bin
sed -i '/server-id/d' /etc/my.cnf
sed -i '/relay-log/d' /etc/my.cnf
sed -i "/\[mysqld\]/a server-id=${server_id}" /etc/my.cnf
sed -i "/\[mysqld\]/a relay-log=${relay_log}" /etc/my.cnf
sed -i "/\[mysqld\]/a slave-skip-errors=all" /etc/my.cnf
sed -i "/\[mysqld\]/a read-only=1" /etc/my.cnf
