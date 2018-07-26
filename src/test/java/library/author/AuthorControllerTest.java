package library.author;

import static java.util.Optional.ofNullable;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import library.ComponentTest;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

public class AuthorControllerTest extends ComponentTest {

  @MockBean
  private AuthorOperation operation;

  @Test
  public void shouldReturnAuthorGivenAnId() throws Exception {
    Author author = Author.builder()
        .id(1L)
        .name("Steve")
        .build();

    given(operation.findById(1L)).willReturn(ofNullable(author));

    mockMvc.perform(get("/author/1").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id", is(1)))
        .andExpect(jsonPath("name", is("Steve")));

    verify(operation).findById(1L);
  }
}