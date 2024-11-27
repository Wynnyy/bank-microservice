package sk.app.customer;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sk.app.exception.BusinessException;
import sk.app.model.dto.CustomerDto;
import sk.app.model.entities.Customer;
import sk.app.model.mapper.CustomersMapper;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomersMapper customersMapper;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomersMapper customersMapper, CustomerRepository customerRepository) {
        this.customersMapper = customersMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> findAll() {
        return customersMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto create(Customer newCustomer) {
        Customer customer = customerRepository.save(newCustomer);
        log.info("Creating new customer with id: [{}]", customer.getId());
        return customersMapper.toDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto modify(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new BusinessException("Customer doesn't exist with id: " + customer.getId(), HttpStatus.NOT_FOUND));

        customer.setAccounts(customer.getAccounts());
        customerRepository.save(customer);
        log.info("Updating customer with id: [{}]", existingCustomer.getId());
        return customersMapper.toDto(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }
}
