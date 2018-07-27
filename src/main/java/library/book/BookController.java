package library.book;

import static library.authentication.AuthenticationConstants.IDENTITY_KEY;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import library.authentication.RequiresPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/book")
public class BookController {

  private final BookOperation bookOperation;

  @PostMapping
  @RequiresPermission
  public @ResponseBody
  void createNewBook(@RequestBody Book book) {
    bookOperation.createBook(book);
  }

  @GetMapping
  @RequiresPermission
  public @ResponseBody
  Iterable<Book> getAllBooks(HttpServletRequest httpServletRequest) {
    String identity = (String) httpServletRequest.getAttribute(IDENTITY_KEY);
    return bookOperation.findAll();
  }

  @GetMapping(path = "{id}")
  @RequiresPermission
  public @ResponseBody
  Optional<Book> searchById(@PathVariable Long id) {
    return bookOperation.findById(id);
  }


  @PutMapping(path = "{id}")
  @RequiresPermission
  public @ResponseBody
  void updateBook(@PathVariable Long id, @RequestBody Book book) {
    bookOperation.updateBook(id, book);
  }

  @DeleteMapping(path = "{id}")
  @RequiresPermission
  public @ResponseBody
  void deleteBookById(@PathVariable Long id) {
    bookOperation.delete(id);
  }

}

