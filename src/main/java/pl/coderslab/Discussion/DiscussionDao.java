package pl.coderslab.Discussion;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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


    public List<Discussion> findAllDiscussions() {
        TypedQuery<Discussion> query = entityManager.createQuery("""
                                SELECT d FROM Discussion d
                """, Discussion.class);
        return query.getResultList();
    }
}
//    public Discussion findDiscussionById(Long id) {
//        return entityManager.find(Discussion.class, id);
//    }

// nie potrzebujemy edycji i usuwania dyskusji

//    public void updateDiscussion(Discussion discussion) {
//        entityManager.merge(discussion);
//    }
//
//    public void deleteDiscussionById(Long id) {
//        Discussion discussion = findDiscussionById(id);
//        entityManager.remove(entityManager.contains(discussion) ? discussion : entityManager.merge(discussion));
//    }