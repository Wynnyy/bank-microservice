package sk.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.app.model.dto.CustomerDto;
import sk.app.model.entities.Customer;

import java.util.List;

@RestController
@RequestMapping(CustomerRestController.CUSTOMER)
public class CustomerRestController {

    public static final String CUSTOMER = "/customer";
    public static final String CREATE_CUSTOMER = "/create";
    public static final String MODIFY_CUSTOMER = "/modify";

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @return list of all Customers with Accounts and Transfers
     */

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    /**
     * create a new customer
     *
     * @param customer
     * @return
     */
    @PostMapping(CREATE_CUSTOMER)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }


    /**
     * updating existing customer
     *
     * @param customer
     * @return
     */
    @PostMapping(MODIFY_CUSTOMER)
    public ResponseEntity<CustomerDto> modifyCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.modify(customer));
    }

    /**
     * delete one customer by id
     *
     * @param id
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<String> deleteCustomer(@RequestParam(value = "id") Long id) {
        customerService.remove(id);
        return ResponseEntity.ok("Customer with ID " + id + " successfully deleted.");
    }
}
