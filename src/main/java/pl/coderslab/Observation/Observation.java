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

    @NotNull
    private LocalDate date;

    @Size(min = 3, max = 100)
    private String description;

    @PrePersist
    public void prePersist() {
        date = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @NotNull
    private Location location;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    @NotNull
    private Animal animal;

    @ToString.Exclude
    @OneToMany(mappedBy = "observation", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Discussion> discussion = new HashSet<>();
}
