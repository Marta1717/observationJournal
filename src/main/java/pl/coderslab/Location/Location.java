package pl.coderslab.Location;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String locationName;
    private String biome;
    private String locationDescription;

    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Animal> animals = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Observation> observations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
