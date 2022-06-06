# 基础镜像使用java
FROM java:11
# 作者
MAINTAINER wangdapeng
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为mayikt.jar
ADD wdp-kiss-1.0-SNAPSHOT.jar kiss.jar
# 运行jar包
RUN bash -c 'touch /kiss.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kiss.jar"]
#暴露8080端口
EXPOSE 8080