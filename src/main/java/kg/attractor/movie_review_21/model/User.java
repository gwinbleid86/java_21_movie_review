package kg.attractor.movie_review_21.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class User {
    @Id
    private String email;
    private String username;
    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Collection<Role> roles;
}
