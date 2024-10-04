package pl.coderslab.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    void deleteUserById(Long id);

    User findUserByUsername(String username);

    User save(@NotNull @Size(min = 3, max = 15) String user);
}

//    User findByUsername(String username);
//
//    User findByEmail(String email);
//
//    @Query("SELECT u FROM User u WHERE u.username LIKE :username%")
//    List<User> findByUsername(String username);
//
//    @Query("SELECT u FROM User u WHERE u.email LIKE :email%")
//    List<User> findByEmail(String email);
//}
