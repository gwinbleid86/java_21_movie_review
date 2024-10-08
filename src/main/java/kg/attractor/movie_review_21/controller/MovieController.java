package kg.attractor.movie_review_21.controller;

import kg.attractor.movie_review_21.dto.MovieRequest;
import kg.attractor.movie_review_21.service.CastService;
import kg.attractor.movie_review_21.service.DirectorService;
import kg.attractor.movie_review_21.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final DirectorService directorService;
    private final CastService castService;

    @GetMapping
    public String getMovies(
            Model model,
            @PageableDefault(size = 3, sort = "releaseYear") Pageable pageable
    ) {
        model.addAttribute("movies", movieService.getMovies(pageable));
        return "movies/index";
    }

    @GetMapping("{id}")
    public String getMovie(@PathVariable long id, Model model) {
        model.addAttribute("movie", movieService.getMovie(id));
        return "movies/movie";
    }

    @GetMapping("create")
    public String createMovie(Model model) {
        model.addAttribute("directors", directorService.getList());
        model.addAttribute("casts", castService.getList());
        return "movies/create";
    }

    @PostMapping("create")
    public String createMovie(MovieRequest movie) {
        movieService.create(movie);
        return "redirect:/";
    }
}
