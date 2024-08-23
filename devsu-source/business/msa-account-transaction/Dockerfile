#CHOISE AN IMAGE
FROM eclipse-temurin:17-jdk-alpine

# SET A WORKDIR WHERE GOIN APP JAR
WORKDIR /app

# COPY GENERATED JAR INTO WORKDIR CONTAINER
COPY target/msa-account-transaction-1.0-SNAPSHOT.jar /app/msa-account-transaction-1.0-SNAPSHOT.jar

# SELECT A PORT WHERE THE IS EXPOSED
EXPOSE 8080

# LAUNCH APP
CMD ["java", "-jar", "/app/msa-account-transaction-1.0-SNAPSHOT.jar"]