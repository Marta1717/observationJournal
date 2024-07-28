package pl.coderslab.Location;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal_Location.AnimalLocation;

import javax.persistence.*;
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
    private String locationName;
    private String biome;
    private String locationDescription;
    private Long userId;

    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AnimalLocation> animalLocations = new HashSet<>();

//    @ManyToOne
//    private User user;

//    public String getUsername() {
//        return user.getUsername();
//    }

//    public void setUser(User user) {
//        this.user = user;
//        if (user != null) {
//            user.getLocations().add(this);
//        }
//    }
}
