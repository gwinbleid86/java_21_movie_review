package kg.attractor.movie_review_21.controller.api;

import kg.attractor.movie_review_21.dto.DirectorDto;
import kg.attractor.movie_review_21.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping
    public List<DirectorDto> getList(){
        return directorService.getList();
    }

    @GetMapping("byMovie")
    public DirectorDto getByMovie(@RequestParam("movieId") int movieId){
        return directorService.getByMovieId(movieId);
    }

    @PostMapping
    public HttpStatus addDirector(@RequestBody DirectorDto directorDto){
        directorService.create(directorDto);
        return HttpStatus.OK;
    }
}
