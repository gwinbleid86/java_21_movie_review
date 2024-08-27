package kg.attractor.movie_review_21.controller.api;

import kg.attractor.movie_review_21.dto.MovieDto;
import kg.attractor.movie_review_21.errors.CanNotFindMovieException;
import kg.attractor.movie_review_21.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("restProfile")
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieDto>> getMovies(Pageable pageable) {
        return new ResponseEntity<>(movieService.getMovies(pageable), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<?> searchMovie(@RequestParam(name = "id", required = false) Integer id) {
        try {
            return new ResponseEntity<>(movieService.getMovie(id), HttpStatus.OK);
        } catch (CanNotFindMovieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMovie(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(movieService.getMovie(id), HttpStatus.OK);
        } catch (CanNotFindMovieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @PostMapping
//    public HttpStatus createMovie(@RequestBody MovieDto movieDto) {
//        movieService.create(movieDto);
//        return HttpStatus.CREATED;
//    }
}
