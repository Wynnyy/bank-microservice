package sk.zentity.customer;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sk.zentity.exception.BusinessException;
import sk.zentity.model.dto.CustomerDto;
import sk.zentity.model.entities.Customer;
import sk.zentity.model.mapper.UniMapper;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private UniMapper uniMapper;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(UniMapper uniMapper, CustomerRepository customerRepository) {
        this.uniMapper = uniMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> findAll() {
        return uniMapper.toDtoListCustomers(customerRepository.findAll());
    }

    @Override
    public CustomerDto create(Customer newCustomer) {
        Customer customer = customerRepository.save(newCustomer);
        log.info("Creating new customer with id: [{}]", customer.getId());
        return uniMapper.toDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto modify(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new BusinessException("Customer doesn't exist with id: " + customer.getId(), HttpStatus.NOT_FOUND));

        customer.setAccounts(customer.getAccounts());
        customerRepository.save(customer);
        log.info("Updating customer with id: [{}]", existingCustomer.getId());
        return uniMapper.toDto(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }
}
