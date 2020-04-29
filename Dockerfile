#如果配置api url是远端的，那么这个dockerfile就是在远端执行
#最小化的java8
FROM anapsix/alpine-java:8_server-jre_unlimited
#指定容器时间
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#创建文件夹
RUN mkdir -p /songling
#设置成工作目录，命令在此目录执行
WORKDIR /songling
#容器服务端口，还需要再docker配置主机访问端口
EXPOSE 8000
#传到工作目录中，如果不是本机docker，那么直接传到远程主机上
ADD ./target/diningroom-0.0.1-SNAPSHOT.jar ./
# Djava.security.egd=file:/dev/./urandom 解决阿里云的tomcat启动慢的问题
CMD sleep 5;java -Djava.security.egd=file:/dev/./urandom -jar diningroom-0.0.1-SNAPSHOT.jar

#ENTRYPOINT ["java","-jar","/songling-0.0.1-SNAPSHOT.jar"]  效果与CMD类似，不可被 docker run 提供的参数覆盖