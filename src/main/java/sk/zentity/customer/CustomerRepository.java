package sk.zentity.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.zentity.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
