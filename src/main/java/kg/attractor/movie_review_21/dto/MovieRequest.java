package kg.attractor.movie_review_21.dto;

import lombok.Data;

@Data
public class MovieRequest {
    private Integer id;
    private String name;
    private Integer year;
    private String description;
    private Long directorId;
    private Long castId;

}
