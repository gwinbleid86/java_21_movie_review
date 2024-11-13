package kg.attractor.movie_review_21.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kg.attractor.movie_review_21.controller.api.MovieController;
import kg.attractor.movie_review_21.dto.MovieDto;
import kg.attractor.movie_review_21.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class MovieSteps {

    private final MovieService movieService;
    private MovieDto movieDto;

    private final MovieController movieController;

    @Autowired
    public MovieSteps(MovieService movieService, MovieController movieController) {
        this.movieService = movieService;
        this.movieController = movieController;
    }

    @When("I request a movie with id {int}")
    public void iRequestAMovieWithId(int movieId) {
        var test = movieController.getMovie(movieId);
        movieDto = (MovieDto) test.getBody();
//        movieDto = movieService.getMovie(movieId);
        log.info("MovieDto: {}", movieDto);
        assertEquals(movieId, movieDto.getId());
    }

    @Then("I get information about the film.")
    public void iGetInformationAboutTheFilm() {
        assertNotNull(movieDto);
    }
}
