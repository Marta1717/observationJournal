package pl.coderslab.Observation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal_Location.AnimalLocation;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.User.User;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate date;
    private String description;

    @PrePersist
    public void prePersist() {
        date = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "observation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AnimalLocation> animalLocations = new HashSet<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Discussion discussions;


//    public void setUser(User user) {
//        this.user = user;
//        if (user != null) {
//            this.userUsername = user.getUsername();
//        }
//    }


    //    public void setAnimal(Animal animal) {
//        this.animal = animal;
//        if (animal != null) {
//            this.animalName = animal.getAnimalName();
//        }
//    }
//
//
//    public void setLocation(Location location) {
//        this.location = location;
//        if (location != null) {
//            this.locationName = location.getLocationName();
//        }
//        if (location != null) {
//            this.locationBiome = location.getBiome();
//        }
//    }


}
