package library.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

  @PostMapping
  public ResponseEntity<AuthenticationResponse> authenticateUser(
      @RequestBody UserCredentials userCredentials) {

    return isValidLogin(userCredentials) ?
        ResponseEntity.ok(new AuthenticationResponse("token")) :
        ResponseEntity.badRequest().build();
  }

  private boolean isValidLogin(@RequestBody UserCredentials userCredentials) {
    return userCredentials.getUsername().equals("will") &&
        userCredentials.getPassword().equals("willisawesome");
  }
}
