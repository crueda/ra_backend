FROM openjdk:20-jdk-oracle
WORKDIR /home
COPY /target/retoautentia-0.0.1-SNAPSHOT.jar retoautentia.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "retoautentia.jar"]