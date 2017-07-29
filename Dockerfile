FROM java:8
VOLUME /tmp
ADD target/search-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=container -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]