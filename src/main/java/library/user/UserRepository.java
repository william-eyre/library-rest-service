package library.user;

import javax.validation.constraints.NotBlank;
import library.authentication.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, Long> {

  UserCredentials findByUsername(@NotBlank String username);

}
