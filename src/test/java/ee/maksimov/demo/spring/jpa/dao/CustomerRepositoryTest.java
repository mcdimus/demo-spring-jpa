package ee.maksimov.demo.spring.jpa.dao;

import ee.maksimov.demo.spring.jpa.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CustomerRepository customers;

  @Test
  public void testFindByLastName() {
    Customer customer = Customer.builder().id(UUID.randomUUID()).firstName("first").lastName("last").build();
    entityManager.persist(customer);

    List<Customer> findByLastName = customers.findByLastName(customer.getLastName());

    assertThat(findByLastName).containsExactly(customer);
  }

}