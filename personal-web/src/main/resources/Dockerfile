FROM centos:7.4.1708
MAINTAINER ydcqy
EXPOSE 8500
COPY /etc/localtime /etc/localtime
COPY @project.build.finalName@.jar @project.build.finalName@.jar
ENV JAVA_HOME /opt/app/jdk-9.0.1
ENV PATH $PATH:$JAVA_HOME/bin
