package kg.attractor.movie_review_21.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageRequestDto {
    private Long movieId;
    private MultipartFile file;
}
