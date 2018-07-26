package library.author;

import java.util.Optional;
import library.book.Book;

public interface AuthorOperation {

  void createAuthor(Author author);

  Iterable<Author> findAll();

  Optional<Author> findById(Long id);
  
  void updateAuthor(Long id, Author author);

  void delete(Long id);

  void addBook(Long id, Book book);
}
