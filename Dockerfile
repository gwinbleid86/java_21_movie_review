FROM openjdk:21
RUN mkdir /app
COPY ./Movie_review*.jar ./app/movie_review.jar
WORKDIR /app

EXPOSE 8089
CMD ["java", "-jar", "movie_review.jar"]