#!/usr/bin/bash
echo 00 > /etc/pki/CA/serial
> /etc/pki/CA/index.txt
# 生成CA根证书
openssl genrsa -out ca.key 2048
openssl req -new -x509 -days 3650 -key ca.key -out ca.pem -subj "/C=CN/O=Yuanda Corporation/OU=Root CA/CN=Yuanda Root CA"

#生成中间证书
openssl genrsa -out ca-sub.key 2048
openssl req -new -key ca-sub.key -out ca-sub.csr -subj "/C=CN/O=Yuanda Corporation/OU=Sub CA/CN=Yuanda Sub CA"
openssl ca -cert ca.pem -keyfile ca.key -extensions v3_ca -in ca-sub.csr -out ca-sub.pem -days 3650 -policy policy_anything

#生成应用证书
openssl genrsa -out app.key 2048
openssl req -new -key app.key -out app.csr -subj "/C=CN/O=Yuanda Corporation/ST=beijing/L=beijing/CN=*.yuanda.com"
openssl ca -cert ca-sub.pem -keyfile ca-sub.key -in app.csr -out app.pem -days 3650 -policy policy_anything

##########################################################
# openssl转jks
openssl pkcs12 -export -inkey app.key -in app.pem -out app.pfx
keytool -importkeystore -srckeystore app.pfx -destkeystore app.jks -srcstoretype PKCS12 -deststoretype JKS