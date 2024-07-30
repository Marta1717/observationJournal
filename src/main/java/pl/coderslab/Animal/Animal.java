package pl.coderslab.Animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Location.Location;
import pl.coderslab.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
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
    private String animalClassis;
    private String animalDescription;

    public static final List<String> CLASSIS = List.of(
            "MAMMAL", "BIRD", "REPTILE", "AMPHIBIAN", "FISH");

    @ToString.Exclude
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Location> locations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
