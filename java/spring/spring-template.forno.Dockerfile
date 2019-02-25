FROM openjdk:11

COPY api/build/libs/api.jar /api.jar

CMD ["java", "-jar", "/api.jar"]

EXPOSE 8080
