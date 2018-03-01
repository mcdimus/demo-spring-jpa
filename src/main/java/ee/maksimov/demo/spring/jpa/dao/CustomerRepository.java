package ee.maksimov.demo.spring.jpa.dao;

import ee.maksimov.demo.spring.jpa.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID>{

  List<Customer> findByLastName(String lastName);

}
