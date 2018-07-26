package library.user;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface UserClient {

  @RequestLine("GET /{username}")
  User findByUsername(@Param("username") String username);

  @RequestLine("GET ")
  List<User> returnAllUsers();

  @RequestLine("POST /")
  @Headers("Content-Type: application/json")
  void createUser(User user);

}
