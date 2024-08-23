#CHOISE AN IMAGE
FROM eclipse-temurin:17-jdk-alpine

# SET A WORKDIR WHERE GOIN APP JAR
WORKDIR /app

# COPY GENERATED JAR INTO WORKDIR CONTAINER
COPY target/msa-customer-1.0-SNAPSHOT.jar /app/msa-customer-1.0-SNAPSHOT.jar

# SELECT A PORT WHERE THE IS EXPOSED
EXPOSE 8080

# LAUNCH APP
CMD ["java", "-jar", "/app/msa-account-customer-1.0-SNAPSHOT.jar"]