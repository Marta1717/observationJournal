package pl.coderslab.Discussion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "discussion")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private LocalDate createdAt;

    @ManyToOne
    private Observation observation;

    @ManyToOne
    private User user;

    public void setObservation(Observation observation) {
        this.observation = observation;
        if (observation.getUser() != null) {
            this.user = observation.getUser();
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (observation.getUser() != null) {
            this.observation.setUser(user);
        }
    }
}
