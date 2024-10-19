package pl.coderslab.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);
}

//    @Query("SELECT u FROM User u WHERE u.username LIKE :username%")
//    List<User> findByUsername(String username);
//}
