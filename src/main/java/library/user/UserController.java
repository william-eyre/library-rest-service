package library.user;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

  private final UserService userService;

  @GetMapping(path = "{username}")
  public @ResponseBody
  User searchByUsername(@PathVariable String username) {
    return userService.searchForUser(username);
  }

  @GetMapping()
  public @ResponseBody
  List<User> returnAllUsers() {
    return userService.returnAllUsers();
  }

  @PostMapping
  public @ResponseBody
  void createUser(@RequestBody User user) {
    userService.createUser(user);
  }
}

