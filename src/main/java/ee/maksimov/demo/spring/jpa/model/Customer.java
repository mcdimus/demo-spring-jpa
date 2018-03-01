package ee.maksimov.demo.spring.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Customer {

  @Id
  private UUID id;
  private String firstName;
  private String lastName;

  public Customer() {
    this.id = UUID.randomUUID();
  }

}
