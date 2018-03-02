package ee.maksimov.demo.spring.jpa.controller;

import ee.maksimov.demo.spring.jpa.AbstractIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class GreetingControllerIT extends AbstractIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getGreeting_ReturnsDefaultMessage_IfNoParam() throws Exception {
    this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
      .andExpect(jsonPath("$.content").value("Hello, World!"));
  }

  @Test
  public void getGreeting_ReturnsTailoredMessage_IfParam() throws Exception {
    this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
      .andDo(print()).andExpect(status().isOk())
      .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
  }

}
