package pl.coderslab.Discussion;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
@Configuration
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    List<Discussion> findDiscussionByUser(User user);

    List<Discussion> findDiscussionByObservation(Observation observation);
}
