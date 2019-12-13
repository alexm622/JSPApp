#!/bin/sh
echo "hello"
#service ssh start
java -jar /usr/local/tomcat/ServerStatusReader.jar &
catalina.sh run

