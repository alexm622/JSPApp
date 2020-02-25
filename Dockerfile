FROM tomcat:9.0
#updates
RUN apt-get update
RUN apt-get -y install zip dos2unix
RUN apt-get -y install libaprutil1
RUN rm -r /usr/local/tomcat/conf
#RUN rm -r /usr/local/tomcat/webapps/ROOT/favicon.ico

#expose the right ports
EXPOSE 80 443

#remove the root directory
RUN ls /usr/local/tomcat/webapps/
#remove the docs
#RUN rm -r /usr/local/tomcat/webapps/docs
#add the hosted files
RUN mkdir /usr/local/tomcat/webapps/hosted
#add the hosted files
ADD docker/Racks.csv /usr/local/tomcat/webapps/hosted/
ADD docker/hosts.bin /usr/local/tomcat/webapps/hosted/
ADD docker/simpleInjector.exe /usr/local/tomcat/webapps/hosted/
#ssl certs
ADD docker/ssl/cert.pem /usr/local/ssl/cert.pem
ADD docker/ssl/privkey.pem /usr/local/ssl/privkey.pem
#recatpcha key
ADD docker/recaptcha.txt /Data/recaptcha.txt

#mysql drivers
ADD docker/mysql-connector-java-5.1.48.jar /usr/local/tomcat/lib
#add configuration
ADD docker/conf /usr/local/tomcat/conf
#the launchscript
ADD docker/launchscript.sh /usr/local/tomcat/bin

#final launchscript setup
RUN chmod 777 /usr/local/tomcat/bin/launchscript.sh
RUN dos2unix /usr/local/tomcat/bin/launchscript.sh

#add the build webapp
ADD target/JSPapp.war /usr/local/tomcat/webapps

#make it the root webapp
RUN mv /usr/local/tomcat/webapps/JSPapp.war /usr/local/tomcat/webapps/ROOT.war

#set the entrypoint
ENTRYPOINT ["/usr/local/tomcat/bin/launchscript.sh"]
