package pl.coderslab.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.Location.Location;
import pl.coderslab.Observation.Observation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
//@Component
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The 'user' field cannot be empty")
    @Column(unique = true)
    @Size(min = 3, max = 15)
    private String username;

    @ToString.Exclude
    @NotNull(message = "The 'password' field cannot be empty")
    @Size(min = 3, max = 15)
    private String password;

    @NotNull(message = "The 'email' field cannot be empty")
    @Email
    private String email;

    private String newsletter;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Observation> observations = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Discussion> discussions = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Animal> animals = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Location> locations = new HashSet<>();
}
