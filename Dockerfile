FROM tomcat:9.0
RUN apt-get update
RUN apt-get -y install zip dos2unix
RUN apt-get -y install libaprutil1
RUN rm -r /usr/local/tomcat/conf
#RUN rm -r /usr/local/tomcat/webapps/ROOT/favicon.ico


EXPOSE 80 443

RUN mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/Homepage
RUN mkdir /usr/local/tomcat/webapps/hosted
ADD docker/Racks.csv /usr/local/tomcat/webapps/hosted/
ADD docker/hosts.bin /usr/local/tomcat/webapps/hosted/
ADD docker/ssl/cert.pem /usr/local/ssl/cert.pem
ADD docker/ssl/privkey.pem /usr/local/ssl/privkey.pem





ADD docker/mysql-connector-java-5.1.48.jar /usr/local/tomcat/lib

ADD docker/conf /usr/local/tomcat/conf
ADD docker/launchscript.sh /usr/local/tomcat/bin
RUN chmod 777 /usr/local/tomcat/bin/launchscript.sh
RUN dos2unix /usr/local/tomcat/bin/launchscript.sh






ADD target/JSPapp.war /usr/local/tomcat/webapps

RUN mv /usr/local/tomcat/webapps/JSPapp.war /usr/local/tomcat/webapps/ROOT.war

ENTRYPOINT ["/usr/local/tomcat/bin/launchscript.sh"]
