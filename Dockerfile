FROM openjdk:17-alpine
ARG JAR_FILE=target/booking-service-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} bookingservice.jar
ENTRYPOINT ["java","-jar","/bookingservice.jar"]