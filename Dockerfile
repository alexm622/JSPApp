FROM tomcat:9.0
RUN apt-get update
RUN apt-get -y install zip dos2unix
RUN rm -r /usr/local/tomcat/conf
#RUN rm -r /usr/local/tomcat/webapps/ROOT/favicon.ico


EXPOSE 8080

RUN mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/Homepage
RUN mkdir /usr/local/tomcat/webapps/hosted
ADD docker/Racks.csv /usr/local/tomcat/webapps/hosted/
ADD docker/hosts.bin /usr/local/tomcat/webapps/hosted/





ADD docker/mysql-connector-java-5.1.48.jar /usr/local/tomcat/lib

ADD docker/conf /usr/local/tomcat/conf
ADD docker/launchscript.sh /usr/local/tomcat/bin
RUN chmod 777 /usr/local/tomcat/bin/launchscript.sh
RUN dos2unix /usr/local/tomcat/bin/launchscript.sh






ADD JSPApp.war /usr/local/tomcat/webapps

RUN mv /usr/local/tomcat/webapps/JSPApp.war /usr/local/tomcat/webapps/ROOT.war

ENTRYPOINT ["/usr/local/tomcat/bin/launchscript.sh"]
