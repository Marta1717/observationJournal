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

    @NotNull(message = "Username cannot be null")
    @Column(unique = true)
    @Size(min = 3, max = 15)
    private String username;

    @ToString.Exclude
    @NotNull(message = "Password cannot be null")
    @Size(min = 3, max = 15)
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

//    private String role;

//    public static final List<String> NEWSLETTER = List.of(
//            "Yes", "No");
    private String newsletter;

//    public boolean hasRole(String role) {
//        return this.role.equals(role);
//    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Observation> observations = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Discussion> discussions = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Animal> animals = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Location> locations = new HashSet<>();
}
