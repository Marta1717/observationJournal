package pl.coderslab.Animal_Location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Location.Location;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "animal_location")
public class AnimalLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

   @ManyToOne
   @JoinColumn(name = "observation_id")
   private Observation observation;

    @ManyToOne
    private User user;
}
