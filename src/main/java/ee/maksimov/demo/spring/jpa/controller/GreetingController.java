package ee.maksimov.demo.spring.jpa.controller;

import ee.maksimov.demo.spring.jpa.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.lang.String.format;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return Greeting.builder()
      .id(UUID.randomUUID())
      .content(format(template, name))
      .build();
  }

}
