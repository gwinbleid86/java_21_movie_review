package kg.attractor.movie_review_21.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kg.attractor.movie_review_21.model.Movie;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieDao {
    private static final Path PATH = Path.of("data/files/movies.json");


    @Getter
    private List<Movie> movies = new ArrayList<>();

    public MovieDao() {
        readFile();
    }

    private void readFile() {
        try {
            String json = Files.readString(PATH);
            Map<String, List<Movie>> list = new Gson()
                    .fromJson(
                            json,
                            new TypeToken<Map<String, List<Movie>>>() {
                            }.getType()
                    );
            movies.addAll(list.get("movies"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void writeFile(Map<String, List<Movie>> moviesMap) {
        String json = new Gson().toJson(moviesMap);
        try {
            Files.write(PATH, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        writeFile(Map.of("movies", movies));
    }
}
