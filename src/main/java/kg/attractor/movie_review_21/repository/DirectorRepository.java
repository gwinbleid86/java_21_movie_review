package kg.attractor.movie_review_21.repository;

import kg.attractor.movie_review_21.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    @Query(
            "select d from Director d, Movie m" +
                    " where d.id = m.director.id and m.id = :movieId"
    )
//    @Query(
//            value = """
//                select * from director d, movie m
//                where d.id = m.director_id
//                and m.id = :movieId;
//                """,
//            nativeQuery = true
//    )
    Optional<Director> findByMovieId(Long movieId);
}
