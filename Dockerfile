FROM java:8
VOLUME /tmp
ADD target/search-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=container"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]