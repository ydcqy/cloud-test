-- 主配置
GRANT REPLICATION SLAVE,REPLICATION CLIENT on *.* to 'slave_replication'@'10.1.7.12' IDENTIFIED by 'xxxx';-- 创建复制账号
show master status;-- 查看主状态

-- 从配置
show global variables like '%relay%';-- 显示中继日志信息
change master to master_host='10.1.7.11',master_port=13106,master_user='slave_replication',master_password='xxxxx',master_log_file='mysql-bin.000002',master_log_pos=506;