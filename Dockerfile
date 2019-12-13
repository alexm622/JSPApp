FROM tomcat:9.0
RUN apt-get update
RUN apt-get -y install zip dos2unix
RUN rm -r /usr/local/tomcat/conf


EXPOSE 8080
ADD JSPApp.war /usr/local/tomcat/webapps
ADD ServerStatusReader.jar /usr/local/tomcat

RUN mkdir /tmp/servervars
RUN chmod 777 /tmp/servervars

ADD conf /usr/local/tomcat/conf
ADD launchscript.sh /usr/local/tomcat/bin
RUN chmod 777 /usr/local/tomcat/bin/launchscript.sh
RUN dos2unix /usr/local/tomcat/bin/launchscript.sh
ENTRYPOINT ["/usr/local/tomcat/bin/launchscript.sh"]
