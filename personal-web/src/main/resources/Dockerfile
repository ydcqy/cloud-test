FROM centos:7.4.1708
MAINTAINER ydcqy
EXPOSE 8500
# set timezone
COPY Shanghai /etc/localtime
COPY @project.build.finalName@.jar @project.build.finalName@.jar
ENV JAVA_HOME /opt/app/jdk
ENV PATH $PATH:$JAVA_HOME/bin
CMD java -jar -Duser.timezone=Asia/Shanghai @project.build.finalName@.jar