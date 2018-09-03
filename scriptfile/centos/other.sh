#!/usr/bin/bash
#防火墙端口
-A INPUT -p tcp -m state --state NEW -m tcp --dport 1:65535 -j ACCEPT
#make erLang
yum install -y ncurses-devel openssl openssl-devel unixODBC-devel gcc-c++

mkdir /home/mongodb/{log,db} -p; chown -R mongod:mongod /home/mongodb;