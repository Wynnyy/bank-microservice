package sk.app.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.app.model.dto.CustomerDto;
import sk.app.model.entities.Customer;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomersMapper implements ConvertService<CustomerDto, Customer> {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setCustomer(customer.getCustomer());
        customerDto.setCardNumber(customer.getCardNumber());
        customerDto.setName(customer.getName());
        customerDto.setSurName(customer.getSurName());
        customerDto.setNationality(customer.getNationality());
        customerDto.setSex(customer.getSex());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setDateOfExpiry(customer.getDateOfExpiry());
        customerDto.setDateOfIssue(customer.getDateOfIssue());
        if (customer.getAccounts() != null) customerDto.setAccounts(accountMapper.toDtoList(customer.getAccounts()));
        else customerDto.setAccounts(new ArrayList<>());

        return customerDto;
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customer> customerList) {
        return customerList.stream()
                .map(this::toDto)
                .toList();
    }
}
