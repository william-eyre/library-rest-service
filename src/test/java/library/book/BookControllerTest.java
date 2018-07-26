package library.book;

import static java.util.Optional.ofNullable;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import library.ComponentTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

public class BookControllerTest extends ComponentTest {

  @MockBean
  private BookOperation operation;

  @Test
  public void shouldReturnBook() throws Exception {
    Book book = Book.builder()
        .id(1L)
        .title("Gardens of the moon")
        .blurb("Flying cities and undead soldiers")
        .isbn(123456)
        .build();

    given(operation.findById(1L)).willReturn(ofNullable(book));

    mockMvc.perform(get("/book/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id", is(1)))
        .andExpect(jsonPath("title", is("Gardens of the moon")))
        .andExpect(jsonPath("blurb", is("Flying cities and undead soldiers")))
        .andExpect(jsonPath("isbn", is(123456)));

    verify(operation).findById(1L);
  }

  @Test
  public void shouldCreateABook() throws Exception {
    Book createBook = Book.builder()
        .id(2L)
        .title("NeverNight")
        .blurb("A school of assassins")
        .isbn(234567)
        .build();

    mockMvc.perform(post("/book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(createBook)))
        .andDo(print())
        .andExpect(status().isOk());

    verify(operation).createBook(eq(createBook));
  }

  @Test
  @Ignore
  public void shouldUpdateOnlyTitle() throws Exception {
    Book book = Book.builder()
        .id(3L)
        .title("The name of the wind")
        .blurb("A smart boy learns some stuff")
        .isbn(345678)
        .build();

    mockMvc.perform(put("/book/3")
        .contentType(APPLICATION_JSON)
        .content("{\"title\":\"name the wind}"))
        .andDo(print())
        .andExpect(status().isOk());

  }
}
