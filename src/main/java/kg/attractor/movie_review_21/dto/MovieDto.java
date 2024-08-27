package kg.attractor.movie_review_21.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String name;
    private Integer year;
    private String description;
    private DirectorDto director;
    private List<CastDto> cast;
}
