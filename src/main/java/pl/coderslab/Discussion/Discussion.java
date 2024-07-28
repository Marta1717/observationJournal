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

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }

    @ManyToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "observation_id")
    private Observation observation;

//    public void setObservation(Observation observation) {
//        this.observation = observation;
//        if (observation != null) {
//            observation.getDiscussions().add(this);
//        }
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//        if (user != null) {
//            observation.getDiscussions().add(this);
//        }
//    }
//
//    public String getUsername() {
//        return user.getUsername();
//    }
}
