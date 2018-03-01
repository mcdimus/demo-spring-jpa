package ee.maksimov.demo.spring.jpa;

import ee.maksimov.demo.spring.jpa.dao.CustomerRepository;
import ee.maksimov.demo.spring.jpa.model.Customer;
import ee.maksimov.demo.spring.jpa.model.CustomerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.UUID;

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

  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      // save a couple of customers
      repository.save(Customer.builder().id(UUID.fromString("c7b103f4-d25b-48ba-92ff-ecaceb27b355")).firstName("Jack").lastName("Bauer").info(CustomerInfo.builder().build()).build());
      repository.save(Customer.builder().id(UUID.fromString("ef362a9a-1d90-42aa-8710-fa947b44fb68")).firstName("Chloe").lastName("O'Brian").info(CustomerInfo.builder().build()).build());
      repository.save(Customer.builder().id(UUID.fromString("40112d44-daad-4b24-98d9-bc16e399802a")).firstName("Kim").lastName("Bauer").info(CustomerInfo.builder().build()).build());
      repository.save(Customer.builder().id(UUID.fromString("986596e2-587d-4a07-a8c6-37ab927552e1")).firstName("David").lastName("Palmer").info(CustomerInfo.builder().build()).build());
      repository.save(Customer.builder().id(UUID.fromString("6117f3c3-3065-4346-a4c7-a836139b5955")).firstName("Michelle").lastName("Dessler").info(CustomerInfo.builder().build()).build());

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      repository.findAll().forEach(customer -> log.info(customer.toString()));
      log.info("");

      // fetch an individual customer by ID
      Optional<Customer> customer = repository.findById(UUID.fromString("c7b103f4-d25b-48ba-92ff-ecaceb27b355"));
      log.info("Customer found with findOne(1L):");
      log.info("--------------------------------");
      log.info(customer.orElseGet(Customer::new).toString());
      log.info("");

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      repository.findByLastName("Bauer").stream()
        .map(Customer::toString)
        .forEach(log::info);
      log.info("");
    };
  }

}
