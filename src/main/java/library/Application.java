package library;

import library.authentication.PermissionCheckInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan
@SpringBootApplication
public class Application implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new PermissionCheckInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/admin/**");
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}