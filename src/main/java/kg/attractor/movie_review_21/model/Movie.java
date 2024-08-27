package kg.attractor.movie_review_21.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "release_year")
    private Integer releaseYear;

    private String description;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<MovieCastMember> movieCastMemberList;
}
