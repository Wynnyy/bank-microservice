package sk.zentity.model.mapper;

import sk.zentity.model.dto.CustomerDto;
import sk.zentity.model.entities.Customer;

import java.util.List;

public interface CustomerDtoMapper {

    CustomerDto toDto(Customer customer);
    List<CustomerDto> toDtoListCustomers(List<Customer> customerList);
}
