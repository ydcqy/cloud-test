#!/usr/bin/bash
downloadUrl="https://cdn.mysql.com//Downloads/MySQL-5.7/mysql-5.7.22-1.el7.x86_64.rpm-bundle.tar";
saveDir="/opt/downloadFile";
saveFileName="mysql-5.7.22-1.el7.x86_64.rpm-bundle.tar";
savePath=${saveDir}/${saveFileName};
isDelAfterInstall=true;
isReinstall=true;
mysqlPort=13106
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION

enableInstall=false;

if [ x"`rpm -qa | grep -i mariadb`" != x ]
then
    rpm -ev --nodeps `rpm -qa | grep -i mariadb`
fi
if [ x"`rpm -qa | grep -i mysql`" != x ]
then
    if [ ${isReinstall} == true ]
    then
        echo 'Uninstall old mysql...'
        rpm -ev --nodeps `rpm -qa | grep -i mysql`
        echo 'Uninstall finished!'
        if [ -f ${savePath} ]
        then
            enableInstall=true
        else
            wget -O ${savePath} ${downloadUrl} && enableInstall=true;
        fi
    else
        echo 'Mysql already exists'
        exit 0
    fi
else
    if [ -f ${savePath} ]
        then
            enableInstall=true
    else
        wget -O ${savePath} ${downloadUrl} && enableInstall=true;
    fi
fi

if [ ${enableInstall} == true ]
then
    echo '=====download finished!=====start install====='
    yum install -y libaio
    yum install -y net-tools
    yum install -y perl
    yum install -y numactl
    tar -xvf ${savePath} -C ${saveDir} && \
    rpm -vih `find ${saveDir}  -iname "mysql*common*"` && \
    rpm -vih `find ${saveDir}  -iname "mysql*libs*" | grep -v compat` && \
    rpm -vih `find ${saveDir}  -iname "mysql*client*"` && \
    rpm -vih `find ${saveDir}  -iname "mysql*server*" | grep -v minimal`
fi
echo '=====install finished!=====start init setup====='

if [ x"`cat /etc/my.cnf | grep '\[mysql\]'`" == x ]
then
    sed -i '$a [mysql]' /etc/my.cnf
fi
sed -i "/\[mysql\]/a auto-rehash" /etc/my.cnf
sed -i '/port/d' /etc/my.cnf
sed -i "/\[mysqld\]/a port=${mysqlPort}" /etc/my.cnf
echo "init finished!"
echo ">>setup auto run..."
systemctl enable mysqld
echo ">>start..."
systemctl restart mysqld
echo "maybe temporary password..."
grep temporary.*password  /var/log/mysqld.log | awk -F '\\[Note\\]' '{print $2}' | tail -1