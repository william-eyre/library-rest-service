package library.book;

import java.util.Optional;

public interface BookOperation {

  void createBook(Book book);

  void updateBook(Long id, Book book);

  Iterable<Book> findAll();

  Optional<Book> findById(Long id);

  void delete(Long id);
}
