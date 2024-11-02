package pl.coderslab.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
