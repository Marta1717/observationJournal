package pl.coderslab.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUserById(Long id) {
        User user = findUserById(id);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    public List<User> findAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("""
                select u from User u
                """, User.class);
        return query.getResultList();
    }

//    public User findByIdWithObservations(Long id) {
//        User user = entityManager.find(User.class, id);
//        if (user != null) {
//            Hibernate.initialize(user.getObservations());
//        }
//        return user;
//    }
    public List<User> findAllUsersWithDetails() {
        TypedQuery<User> query = entityManager.createQuery("""
                select u from User u
                left join fetch u.animals
                left join fetch u.discussions
                left join fetch u.observations
                """, User.class);
        return query.getResultList();
    }
}
