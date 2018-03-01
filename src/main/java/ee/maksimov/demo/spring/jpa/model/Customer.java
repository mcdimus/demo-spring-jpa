package ee.maksimov.demo.spring.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Customer {

  @Id
  private UUID id;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotNull
  @Type(type = "jsonb")
  @Column(columnDefinition = "jsonb")
  private CustomerInfo info;

  public Customer() {
    this.id = UUID.randomUUID();
  }

}
