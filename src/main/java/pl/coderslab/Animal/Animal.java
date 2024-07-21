package pl.coderslab.Animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "animal")

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String animalClassis;
    private String animalDescription;

    public static final List<String> CLASSIS = List.of(
            "MAMMAL", "BIRD", "REPTILE", "AMPHIBIAN", "FISH");

    @ToString.Exclude
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations = new ArrayList<>();

    @ManyToOne
    private User user;

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            user.getAnimals().add(this);
        }
    }
}
