#!/bin/sh
echo "hello"
screen java -jar /usr/local/tomcat/ServerStatusReader.jar & 
catalina.sh run
