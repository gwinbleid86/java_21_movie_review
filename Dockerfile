#FROM maven:3.9.8-amazoncorretto-21 AS build
#WORKDIR /build
#COPY src ./src
#COPY pom.xml ./
#RUN mvn clean package -DskipTests

FROM openjdk:21
RUN mkdir /app
#WORKDIR /app
#COPY --from=build /build/target/Movie_review*jar ./movie_review.jar
COPY ./target/movie_review*.jar ./app/movie_review.jar
WORKDIR /app

EXPOSE 8089
CMD ["java", "-jar", "movie_review.jar"]