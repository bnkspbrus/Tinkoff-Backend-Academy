FROM amazoncorretto:17

COPY build/libs/HandymanService-0.0.1-SNAPSHOT.jar application.jar
COPY src/main/resources/load-data.js load-data.js

ENTRYPOINT ["java", "-jar", "application.jar"]