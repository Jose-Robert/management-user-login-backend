FROM openjdk:11
COPY target/management-user-login.jar /app/management-user-login.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "management-user-login.jar"]