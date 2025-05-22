FROM eclipse-temurin:17-jre-jammy
COPY target/*.jar app.jar
EXPOSE 8071
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]