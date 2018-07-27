package library.user;

import library.authentication.RequiresNoPermission;
import library.authentication.UserCredentials;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

  private final UserService userService;

  @PostMapping
  @RequiresNoPermission
  public @ResponseBody
  void createNewUser(@RequestBody UserCredentials user) {
    userService.createUser(user);
  }

}
