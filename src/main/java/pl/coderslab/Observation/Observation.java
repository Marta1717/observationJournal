package pl.coderslab.Observation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.Location.Location;
import pl.coderslab.User.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDateTime date;
    private String animalName;
    private String locationName;
    private String locationBiome;
    private String userUsername;
    private String description;


    @PrePersist
    public void prePersist() {
        date = LocalDateTime.now();
    }

    @ManyToOne
    private Animal animal;

    @ManyToOne
    private User user;

    @ManyToOne
    private Location location;

    @ToString.Exclude
    @OneToMany(mappedBy = "observation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Discussion> discussions = new HashSet<>();

    public void setAnimal(Animal animal) {
        this.animal = animal;
        if (animal != null) {
            this.animalName = animal.getName();
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userUsername = user.getUsername();
        }
    }

    public void setLocation(Location location) {
        this.location = location;
        if (location != null) {
            this.locationName = location.getName();
        }
        if (location != null) {
            this.locationBiome = location.getBiome();
        }
    }
}
