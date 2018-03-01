package ee.maksimov.demo.spring.jpa.dao;

import ee.maksimov.demo.spring.jpa.model.Customer;
import ee.maksimov.demo.spring.jpa.model.CustomerInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRepositoryIT extends AbstractIT {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private CustomerRepository repository;

  @Test
  public void save_Saves_IfValidCustomer() {
    // given
    Customer customer = Customer.builder()
      .id(UUID.fromString("c7b103f4-d25b-48ba-92ff-ecaceb27b355"))
      .firstName("Jack")
      .lastName("Bauer")
      .info(CustomerInfo.builder().age(30).build())
      .build();

    // when
    Customer result = repository.save(customer);

    // then
    assertThat(result).isEqualTo(customer);

    Customer savedCustomer = entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getSingleResult();
    assertThat(savedCustomer).isEqualTo(customer);
  }

}
