package library.author;


import static org.assertj.core.api.Assertions.assertThat;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.distribution.Version;
import java.util.Optional;
import library.book.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorServiceTest {

  @Autowired
  private AuthorService underTest;

  @Autowired
  private AuthorRepository authorRepository;

  @Test
  public void shouldCreateAuthor() {
    EmbeddedMysql.anEmbeddedMysql(Version.v5_5_40).start();

    Author jim = authorRepository.save(Author.builder()
        .name("Jim")
        .build());

    underTest.addBook(jim.getId(), Book.builder()
        .id(20L)
        .title("Book")
        .blurb("blurb")
        .isbn(123456)
        .build());

    Optional<Author> author = authorRepository.findById(jim.getId());

    assertThat(author).isPresent()
        .hasValueSatisfying(existingAuthor -> assertThat(existingAuthor.getBooksWriten()));

  }
}