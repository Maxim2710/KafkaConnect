FROM amazoncorretto:21-alpine

EXPOSE 8080

WORKDIR /kafka/app

COPY ./target/*.jar .

CMD java -jar *.jar