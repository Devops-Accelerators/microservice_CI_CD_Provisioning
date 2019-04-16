FROM tomcat:8.5

RUN apt-get update && apt-get -y upgrade

WORKDIR /usr/local/tomcat

ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ADD context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
COPY target/BasicApp-0.0.1-SNAPSHOT.war   /usr/local/tomcat/webapps

EXPOSE 8080
