package kg.attractor.movie_review_21.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String name;
    private Integer year;
    private String description;
    private Director director;
    private List<Cast> cast;
}
