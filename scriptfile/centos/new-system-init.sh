#!/usr/bin/bash
# 设置网卡信息
IPADDR=10.1.7.11
HWADDR=00:0C:29:58:3C:11
hostname=t11
GATEWAY=10.1.7.2

NETMASK=255.255.255.0
DEVICE=eth0
ONBOOT=yes
BOOTPROTO=static
echo "DEVICE=${DEVICE}
ONBOOT=${ONBOOT}
BOOTPROTO=${BOOTPROTO}
IPADDR=${IPADDR}
HWADDR=${HWADDR}
DNS1=${GATEWAY}
NETMASK=${NETMASK}" > /etc/sysconfig/network-scripts/ifcfg-${DEVICE}
echo "0.0.0.0/0 via ${GATEWAY} dev ${DEVICE}" > /etc/sysconfig/network-scripts/route-${DEVICE}
#设置计算机名
hostnamectl set-hostname ${hostname:-t}

# 禁用NetworkManager
systemctl stop NetworkManager
systemctl disable NetworkManager

# 禁用预测命名规则
if [ x"`cat /etc/default/grub | grep 'net.ifnames'`" == x ]
then
    sed -i 's/GRUB_CMDLINE_LINUX.*[^\\"]/& net.ifnames=0/g' /etc/default/grub
    grub2-mkconfig -o /etc/grub2.cfg
fi

#设置源——阿里源
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.bak
mv /etc/yum.repos.d/epel.repo /etc/yum.repos.d/epel.repo.bak
curl https://mirrors.aliyun.com/repo/Centos-7.repo > /etc/yum.repos.d/CentOS-Base.repo
curl https://mirrors.aliyun.com/repo/epel-7.repo > /etc/yum.repos.d/epel.repo
#设置源——docker源
curl http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo > /etc/yum.repos.d/docker-ce.repo
yum clean all
yum makecache

#常用软件安装与设置
systemctl stop firewalld.service
systemctl disable firewalld.service
yum install -y iptables-services
systemctl enable iptables.service
systemctl restart iptables.service
yum install -y ntp
systemctl enable ntpd
systemctl restart ntpd
yum install -y wget
yum install -y bash-completion
yum install -y net-tools
yum install -y vim-enhanced


#免密登录
