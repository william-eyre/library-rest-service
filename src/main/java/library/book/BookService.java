package library.book;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService implements BookOperation {

  private final BookRepository bookRepository;

  @Override
  public void createBook(Book book) {
    book = Book.builder()
        .title(book.getTitle())
        .blurb(book.getBlurb())
        .isbn(book.getIsbn())
        .build();

    bookRepository.save(book);
  }

  @Override
  public void updateBook(Long id, Book book) {
    book = Book.builder()
        .id(id)
        .title(book.getTitle())
        .blurb(book.getBlurb())
        .isbn(book.getIsbn())
        .build();

    bookRepository.save(book);
  }

  public Iterable<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public Optional<Book> findById(Long id) {
    return bookRepository.findById(id);
  }

  @Override
  public void delete(Long id) {
    bookRepository.deleteById(id);
  }
}


