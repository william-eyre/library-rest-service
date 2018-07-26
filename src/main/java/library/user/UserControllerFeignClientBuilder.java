package library.user;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserControllerFeignClientBuilder {

  @Bean
  public UserClient userClient() {
    return Feign.builder()
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .target(UserClient.class, "http://localhost:9999/user");
  }
}
