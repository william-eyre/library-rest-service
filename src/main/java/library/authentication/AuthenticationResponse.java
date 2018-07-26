package library.authentication;

import lombok.Value;

@Value
public class AuthenticationResponse {

  private final String token;
}
