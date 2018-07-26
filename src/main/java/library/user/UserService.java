package library.user;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserClient userClient;

  public User searchForUser(String username) {
    return userClient.findByUsername(username);
  }

  public void createUser(User user) {
    user = User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .build();

    userClient.createUser(user);
  }

  public List<User> returnAllUsers() {
    return userClient.returnAllUsers();
  }
}
