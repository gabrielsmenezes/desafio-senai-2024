FROM maven:3.9-amazoncorretto-17
COPY . .
WORKDIR /backend
RUN mvn clean install -DskipTests
ENTRYPOINT ["mvn" ,"spring-boot:run"]
