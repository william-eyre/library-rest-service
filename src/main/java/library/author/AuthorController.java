package library.author;

import java.util.Optional;
import library.authentication.RequiresPermission;
import library.book.Book;
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
@RequestMapping(path = "/author")
public class AuthorController {

  private final AuthorOperation authorOperation;

  @PostMapping
  @RequiresPermission
  public @ResponseBody
  void createAuthor(@RequestBody Author author) {
    authorOperation.createAuthor(author);
  }

  @GetMapping
  @RequiresPermission
  public @ResponseBody
  Iterable<Author> getAllAuthors() {
    return authorOperation.findAll();
  }

  @GetMapping(path = "{id}")
  @RequiresPermission
  public @ResponseBody
  Optional<Author> searchById(@PathVariable Long id) {
    return authorOperation.findById(id);
  }

  @PutMapping(path = "{id}")
  @RequiresPermission
  public @ResponseBody
  void updateAuthor(@PathVariable Long id, @RequestBody Author author) {
    authorOperation.updateAuthor(id, author);
  }

  @DeleteMapping(path = "{id}")
  @RequiresPermission
  public @ResponseBody
  void deleteAuthor(@PathVariable Long id) {
    authorOperation.delete(id);
  }

  @PostMapping(path = "{id}/addBook")
  @RequiresPermission
  public @ResponseBody
  void addBook(@PathVariable Long id, @RequestBody Book book) {
    authorOperation.addBook(id, book);
  }

}
