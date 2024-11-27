package sk.app.customer;

import sk.app.model.dto.CustomerDto;
import sk.app.model.entities.Customer;

import java.util.List;

public interface CustomerService {


    List<CustomerDto> findAll();
    CustomerDto create(Customer newCustomer);
    CustomerDto modify(Customer customer);
    void remove(Long id);
}
