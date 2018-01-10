FROM tomcat:8-alpine

ADD web/target/rct.war $CATALINA_HOME/webapps/

EXPOSE 8080
