package kg.attractor.movie_review_21.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private Long id;
    private String filename;
    private Long movieId;
}
