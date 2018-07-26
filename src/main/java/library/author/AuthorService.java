package library.author;

import java.util.Optional;
import library.book.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService implements AuthorOperation {

  private final AuthorRepository authorRepository;

  @Override
  public void createAuthor(Author author) {
    Author newAuthor = Author.builder()
        .name(author.getName())
        .build();

    authorRepository.save(newAuthor);
  }

  @Override
  public Iterable<Author> findAll() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> findById(Long id) {
    return authorRepository.findById(id);
  }

  @Override
  public void updateAuthor(Long id, Author author) {
    author = Author.builder()
        .id(id)
        .name(author.getName())
        .build();

    authorRepository.save(author);

  }

  @Override
  public void delete(Long id) {
    authorRepository.deleteById(id);
  }

  @Override
  public void addBook(Long authorId, Book book) {
    authorRepository.findById(authorId).ifPresent(author -> {
      author.getBooksWriten().add(book);
      authorRepository.save(author);
    });
  }
}
