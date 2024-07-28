package pl.coderslab.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal_Location.AnimalLocation;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.Observation.Observation;

import javax.persistence.*;
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
    private String username;

    @ToString.Exclude
    private String password;
    private String email;
    private String newsletter;

//    public static final List<String> NEWSLETTERAGREE = List.of(
//            "Yes", "No");

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Observation> observations = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Discussion> discussions = new HashSet<>();

//    @ToString.Exclude
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private Set<Animal> animals = new HashSet<>();
//
//    @ToString.Exclude
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private Set<Location> locations = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<AnimalLocation> animalLocations = new HashSet<>();
}