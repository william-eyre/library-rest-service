package library.authentication;

import static java.lang.String.format;
import static library.authentication.AuthenticationConstants.KEY;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import library.encryption.PasswordEncryption;
import library.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

  private final UserRepository userRepository;
  private final PasswordEncryption passwordEncryption;

  @PostMapping
  @RequiresNoPermission
  public ResponseEntity<AuthenticationResponse> authenticateUser(
      @RequestBody UserCredentials userCredentials) {

    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

    log.info(format("Authenticated user %s ", userCredentials.getId()));

    return isValidLogin(userCredentials) ?
        ResponseEntity.ok(new AuthenticationResponse(constructJWT(now, userCredentials))) :
        ResponseEntity.badRequest().build();
  }

  private boolean isValidLogin(@RequestBody UserCredentials userCredentials) {
    UserCredentials user = userRepository
        .findByUsername(userCredentials.getUsername());

    return userCredentials.getUsername().equals(user.getUsername()) &&
        passwordEncryption.verifyPassword(userCredentials.getPassword(), user.getPassword());
  }

  private String constructJWT(ZonedDateTime now, UserCredentials user) {

    return Jwts.builder()
        .setClaims(Jwts.claims()
            .setIssuedAt(Date.from(now.toInstant()))
            .setExpiration(Date.from(now.plusHours(1).toInstant())))
        .claim("identity", "Hello I am jim")
        .claim("username", user.getUsername())
        .signWith(SignatureAlgorithm.HS512, KEY)
        .compact();
  }
}
