#!/bin/sh
echo "hello"
#service ssh start
java -jar /usr/local/tomcat/ServerStatusReader.jar &
#catalina.sh run
while [ True]
do
   cat /tmp/servervars/serverstatus.bin
   sleep 10
done
