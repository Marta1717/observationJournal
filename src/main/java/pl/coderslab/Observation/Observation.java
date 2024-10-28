package pl.coderslab.Observation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.Location.Location;
import pl.coderslab.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "The 'date' field cannot be empty")
    private LocalDate date;

    @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters long")
    private String description;

    @PrePersist
    public void prePersist() {
        date = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "The 'user' field cannot be empty")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @NotNull(message = "The 'location' field cannot be empty")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    @NotNull(message = "The 'animal' field cannot be empty")
    private Animal animal;

    @ToString.Exclude
    @OneToMany(mappedBy = "observation", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Discussion> discussion = new HashSet<>();
}
