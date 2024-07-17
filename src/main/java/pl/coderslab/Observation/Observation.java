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
import java.util.ArrayList;
import java.util.List;

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
    private String biome;
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

    @OneToMany(mappedBy = "observation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discussion> discussions = new ArrayList<>();
}
