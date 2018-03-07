FROM centos:7.4.1708
MAINTAINER ydcqy-pptpd
EXPOSE 1723
# set timezone
COPY Shanghai /etc/localtime
COPY /etc/yum.repos.d/epel.repo personal-web:/etc/yum.repos.d/epel.repo
RUN yum install -y pptpd

