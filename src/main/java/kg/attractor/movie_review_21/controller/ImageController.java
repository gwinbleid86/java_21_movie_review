package kg.attractor.movie_review_21.controller;

import kg.attractor.movie_review_21.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<String> upload(MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.upload(file));
    }

    @GetMapping("byName")
    public ResponseEntity<?> download(@RequestParam(name = "name") String name) {
        return imageService.download(name);
    }
}
