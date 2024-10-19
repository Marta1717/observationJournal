package pl.coderslab.Discussion;

import org.springframework.stereotype.Service;
import pl.coderslab.Observation.Observation;
import pl.coderslab.User.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;

    public DiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    @Transactional
    public void saveDiscussion (Discussion discussion) {
        discussionRepository.save(discussion);
    }

    public Discussion findById(Long id) {
        return discussionRepository.findById(id).orElse(null);
    }

    public List<Discussion> findAllDiscussion() {
        return discussionRepository.findAll();
    }

    @Transactional
    public void delete(Discussion discussion) {
        discussionRepository.delete(discussion);
    }

    public List<Discussion> findDiscussionByUser (User user) {
        return discussionRepository.findDiscussionByUser(user);
    }

    public List<Discussion> findDiscussionByObservation(Observation observation) {
        return discussionRepository.findDiscussionByObservation(observation);
    }
}
