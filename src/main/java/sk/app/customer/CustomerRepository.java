package sk.app.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.app.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
