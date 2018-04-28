#!/usr/bin/bash
# 设置网卡信息
IPADDR=10.1.7.11
HWADDR=00:0C:29:58:3C:ED
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

# 禁用NetworkManager
systemctl stop NetworkManager
systemctl disable NetworkManager

# 禁用预测命名规则
if [ x"`cat /etc/default/grub | grep 'net.ifnames'`" == x ]
then
    sed -i 's/GRUB_CMDLINE_LINUX.*[^\\"]/& net.ifnames=0/g' /etc/default/grub
    grub2-mkconfig -o /etc/grub2.cfg
fi

# 设置阿里源