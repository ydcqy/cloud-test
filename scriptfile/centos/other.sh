#!/usr/bin/bash
#防火墙端口
-A INPUT -p tcp -m state --state NEW -m tcp --dport 1:65535 -j ACCEPT


