FROM java:8
VOLUME /tmp
# supermarket-server-0.0.1-SNAPSHOT.jar是打包之后jar的名字
ADD blog_server-0.0.1-SNAPSHOT.jar /test.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/test.jar"]
