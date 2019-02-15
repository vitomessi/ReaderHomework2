FROM  glassfish:latest


COPY ./ReaderWriter-ear.ear $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/

# verbose causes the process to remain in the foreground so that docker can track it

EXPOSE 8080 4048 8081

CMD ["asadmin","start-domain","--verbose"]

