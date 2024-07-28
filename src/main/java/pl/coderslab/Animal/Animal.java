package pl.coderslab.Animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal_Location.AnimalLocation;

import javax.persistence.*;
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
    private String animalName;
    private String animalClassis;
    private String animalDescription;
    private Long userId;

    public static final List<String> CLASSIS = List.of(
            "MAMMAL", "BIRD", "REPTILE", "AMPHIBIAN", "FISH");

    @ToString.Exclude
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AnimalLocation> animalLocation = new HashSet<>();

//    @ManyToOne
//    private User user;

//    public void setUser(User user) {
//        this.user = user;
//        if (user != null) {
//            user.getAnimals().add(this);
//        }
//    }
}
