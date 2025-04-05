FROM maven:3.9.9-amazoncorretto-21-alpine

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

RUN cp ./target/*.jar yuuzaa.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "yuuzaa.jar"]
