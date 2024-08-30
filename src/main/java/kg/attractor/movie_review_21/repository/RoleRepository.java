package kg.attractor.movie_review_21.repository;

import kg.attractor.movie_review_21.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String guest);
}
