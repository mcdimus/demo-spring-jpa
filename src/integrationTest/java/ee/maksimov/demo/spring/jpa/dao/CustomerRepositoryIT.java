package ee.maksimov.demo.spring.jpa.dao;

import ee.maksimov.demo.spring.jpa.model.Customer;
import ee.maksimov.demo.spring.jpa.model.CustomerInfo;
import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRepositoryIT extends AbstractIT {

  private static final String JPQL_GET_BY_ID = "SELECT c FROM Customer c WHERE c.id = :id";

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private CustomerRepository repository;

  @Before
  public void setUp() throws Exception {
    Resource data = resourceLoader.getResource("classpath:db/data/customer.sql");
    try (Stream<String> statements = Files.lines(Paths.get(data.getURI()))) {
      statements.map(entityManager::createNativeQuery)
        .forEach(Query::executeUpdate);
    }
  }

  @Test
  public void save_SavesNewCustomer_IfValidData() {
    // given
    UUID customerId = UUID.fromString("c7b103f4-d25b-48ba-92ff-ecaceb27b355");
    Customer customer = Customer.builder()
      .id(customerId)
      .firstName("Jack")
      .lastName("Bauer")
      .info(CustomerInfo.builder().age(30).build())
      .build();

    // when
    Customer result = repository.save(customer);

    // then
    assertThat(result).isNotSameAs(customer).isEqualTo(customer);

    Customer savedCustomer = entityManager.createQuery(JPQL_GET_BY_ID, Customer.class)
      .setParameter("id", customerId)
      .getSingleResult();
    assertThat(savedCustomer).isEqualTo(customer);
  }

  @Test
  public void save_UpdatesExistingCustomer_IfValidData() {
    // given
    UUID customerId = UUID.fromString("e97df039-111b-4564-abd0-c0429f5964bf");
    Customer customer = Customer.builder()
      .id(customerId)
      .firstName("Sam")
      .lastName("Gamgee")
      .info(CustomerInfo.builder()
        .age(59)
        .premium(false)
        .list(asList("shire", "kind"))
        .map(Maps.newHashMap("weapon", "no"))
        .build())
      .build();

    // when
    Customer result = repository.save(customer);

    //then
    assertThat(result).isNotSameAs(customer).isEqualTo(customer);

    Customer savedCustomer = entityManager.createQuery(JPQL_GET_BY_ID, Customer.class)
      .setParameter("id", customerId)
      .getSingleResult();
    assertThat(savedCustomer).isEqualTo(customer);
  }

  @Test
  public void findByLastName_ReturnsCustomer_IfFound() {
    // given
    String lastName = "Gamgee";

    // when
    List<Customer> result = repository.findByLastName(lastName);

    //then
    Map<String, String> map = new HashMap<>();
    map.put("race", "hobbit");
    map.put("nickname", "The Gardener");
    assertThat(result).containsExactly(
      Customer.builder()
        .id(UUID.fromString("e97df039-111b-4564-abd0-c0429f5964bf"))
        .firstName("Sam")
        .lastName("Gamgee")
        .info(CustomerInfo.builder()
          .age(59)
          .premium(false)
          .list(asList("no swimming", "elvish bread", "shire"))
          .map(map)
          .build())
        .build()
    );
  }

  @Test
  public void count_3_IfThreeEntriesInDb() {
    // when
    long result = repository.count();

    //then
    assertThat(result).isEqualTo(3);
  }

}
