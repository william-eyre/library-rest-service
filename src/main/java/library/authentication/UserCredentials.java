package library.authentication;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCredentials {

  @NotBlank
  private final String username;
  @NotBlank
  private final String password;

}
