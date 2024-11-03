package pl.coderslab.Animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Location.Location;
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
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    private String animalName;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CATEGORY category;
    private String animalDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ToString.Exclude
    @OneToMany(mappedBy = "animal", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Observation> observations = new HashSet<>();
}
