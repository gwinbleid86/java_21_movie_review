package kg.attractor.movie_review_21.controller.api;

import kg.attractor.movie_review_21.dto.ImageRequestDto;
import kg.attractor.movie_review_21.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<String> upload(ImageRequestDto request) {
        imageService.upload(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("byName")
    public ResponseEntity<?> download(@RequestParam(name = "id") Long id) {
        return imageService.download(id);
    }
}
