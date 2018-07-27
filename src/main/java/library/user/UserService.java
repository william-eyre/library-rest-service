package library.user;

import library.authentication.UserCredentials;
import library.encryption.PasswordEncryption;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncryption passwordEncryption;

  public void createUser(UserCredentials userCredentials) {
    String password = passwordEncryption.generatePassword(userCredentials.getPassword());

    log.info("Created user " + userCredentials.getId());

    userRepository.save(UserCredentials.builder()
        .id(userCredentials.getId())
        .username(userCredentials.getUsername())
        .password(password)
        .build());
  }
}

