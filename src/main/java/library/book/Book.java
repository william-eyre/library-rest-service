package library.book;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import library.author.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String title;
  private String blurb;
  private int isbn;

  @ManyToOne
  private Author author;

}
