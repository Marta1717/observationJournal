package pl.coderslab.Discussion;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DiscussionDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveDiscussion(Discussion discussion) {
        entityManager.persist(discussion);
    }

    public Discussion findDiscussionById(Long id) {
        return entityManager.find(Discussion.class, id);
    }

    public void updateDiscussion(Discussion discussion) {
        entityManager.merge(discussion);
    }

    public void deleteDiscussionById(Long id) {
        Discussion discussion = findDiscussionById(id);
        entityManager.remove(entityManager.contains(discussion) ? discussion : entityManager.merge(discussion));
    }

    public List<Discussion> findAllDiscussions() {
        Query query = entityManager.createQuery("""
                    select d from Discussion d
                    left join fetch d.observation
                """);
        List<Discussion> discussions = query.getResultList();
        return query.getResultList();
    }
}
