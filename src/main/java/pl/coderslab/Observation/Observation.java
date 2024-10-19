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

@Entity
@Getter
@Setter
@ToString
@Table(name = "observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data nie może być pusta")
    private LocalDate date;

    @Size(min = 3, max = 500, message = "Opis musi mieć od 3 do 500 znaków")
    private String description;

    @PrePersist
    public void prePersist() {
        date = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Pole 'user' nie może być puste")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @NotNull(message = "Pole 'location' nie może być puste")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    @NotNull(message = "Pole 'animal' nie może być puste")
    private Animal animal;

    @OneToOne(mappedBy = "observation", orphanRemoval = true, fetch = FetchType.EAGER)
    private Discussion discussion;
}
