package pl.coderslab.Discussion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @NotNull(message = "Data nie może być pusta")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "observation_id", referencedColumnName = "id")
    private Observation observation;
}
