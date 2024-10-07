package pl.coderslab.User;

import org.springframework.stereotype.Service;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.Location.Location;
import pl.coderslab.Observation.Observation;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    public List<User> findUserById(Long id) {
        return (List<User>) userRepository.findUserById(id);
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
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Invalid username or password");
    }

    public List<Observation> getUserObservations(Long userId) {
        User user = userRepository.findUserById(userId);
        return new ArrayList<>(user.getObservations());
    }

    public List<Discussion> getUserDiscussions(Long userId) {
        User user = userRepository.findUserById(userId);
        return new ArrayList<>(user.getDiscussions());
    }

    public List<Location> getUserLocations(Long userId) {
        User user = userRepository.findUserById(userId);
        return new ArrayList<>(user.getLocations());
    }

    @Transactional
    public void subscribeToNewsletter(Long userId) {
        User user = userRepository.findUserById(userId);
        user.setNewsletter("subscribed");
        userRepository.save(user);
    }

    @Transactional
    public void unsubscribeFromNewsletter(Long userId) {
        User user = userRepository.findUserById(userId);
        user.setNewsletter("unsubscribed");
        userRepository.save(user);
    }

    public void registerUser(@NotNull(message = "Username cannot be null") @Size(min = 3, max = 15) String username, @NotNull(message = "Password cannot be null") @Size(min = 3, max = 15) String password, @NotNull(message = "Email cannot be null") @Email String email) {
    }

}
