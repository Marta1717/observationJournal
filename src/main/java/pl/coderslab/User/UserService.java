package pl.coderslab.User;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Transactional
    public void saveUser(User user) {
        // Można dodać walidację
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Invalid username or password");
    }

//    public List<Discussion> getUserDiscussions(Long id) {
//        User user = userRepository.findUserById(id);
//        return new ArrayList<>(user.getDiscussions());
//    }

    @Transactional
    public void subscribeToNewsletter(Long userId) {
        User user = userRepository.findUserById(userId);
        user.setNewsletter("YES");
        userRepository.save(user);
    }

    @Transactional
    public void unsubscribeFromNewsletter(Long userId) {
        User user = userRepository.findUserById(userId);
        user.setNewsletter("NO");
        userRepository.save(user);
    }
}
