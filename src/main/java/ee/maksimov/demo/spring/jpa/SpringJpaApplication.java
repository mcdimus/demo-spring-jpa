package ee.maksimov.demo.spring.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringJpaApplication extends SpringBootServletInitializer {

  private static final Logger log = LoggerFactory.getLogger(SpringJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringJpaApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringJpaApplication.class);
  }

}
