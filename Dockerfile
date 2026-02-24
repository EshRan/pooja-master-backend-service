FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY app/target/pooja-market-data-service.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
