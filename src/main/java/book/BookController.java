package book;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/book")
public class BookController {

  @Autowired
  BookRepository repository;

  @GetMapping(path = "/add")
  public @ResponseBody
  String addNewBook(@RequestParam String title,
      @RequestParam String blurb,
      @RequestParam int isbn) {

    Book book = new Book();

    book.setTitle(title);
    book.setBlurb(blurb);
    book.setIsbn(isbn);

    repository.save(book);

    return "Saved";
  }

  @GetMapping
  public @ResponseBody
  Iterable<Book> getAllBooks() {
    return repository.findAll();
  }

  @GetMapping(path = "{id}")
  public @ResponseBody
  Optional<Book> searchById(@PathVariable Long id) {
    return repository.findById(id);
  }

  @GetMapping(path = "/count")
  public @ResponseBody
  String getNumberOfBooks() {
    return "There are " + repository.count() + " books in the library";
  }

}
