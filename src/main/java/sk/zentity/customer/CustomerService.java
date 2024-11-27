package sk.zentity.customer;

import sk.zentity.model.dto.CustomerDto;
import sk.zentity.model.entities.Customer;

import java.util.List;

public interface CustomerService {


    List<CustomerDto> findAll();
    CustomerDto create(Customer newCustomer);
    CustomerDto modify(Customer customer);
    void remove(Long id);
}
