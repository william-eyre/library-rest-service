package library.book;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
  public @ResponseBody
  void createNewBook(@RequestBody Book book) {
    bookOperation.createBook(book);
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public @ResponseBody
  Iterable<Book> getAllBooks() {
    return bookOperation.findAll();
  }

  @GetMapping(path = "{id}")
  public @ResponseBody
  Optional<Book> searchById(@PathVariable Long id) {
    return bookOperation.findById(id);
  }


  @PutMapping(path = "{id}")
  public @ResponseBody
  void updateBook(@PathVariable Long id, @RequestBody Book book) {
    bookOperation.updateBook(id, book);
  }

  @DeleteMapping(path = "{id}")
  public @ResponseBody
  void deleteBookById(@PathVariable Long id) {
    bookOperation.delete(id);
  }

}

