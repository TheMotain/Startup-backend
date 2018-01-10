FROM tomcat:8-alpine

ADD web/target/docker.war $CATALINA_HOME/webapps/

EXPOSE 8080

