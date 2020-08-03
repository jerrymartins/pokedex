FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/pokedex-0.0.1-SNAPSHOT.jar pokedex.jar
ENTRYPOINT ["java","-jar","/pokedex.jar"]