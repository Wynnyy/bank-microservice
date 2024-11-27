package sk.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.app.model.dto.AccountDto;
import sk.app.model.entities.Account;

import java.math.BigDecimal;

@RestController
@RequestMapping(AccountRestController.ACCOUNT)
public class AccountRestController {

    public static final String ACCOUNT = "/account";
    public static final String CREATE_ACCOUNT = "/create";
    public static final String MODIFY_ACCOUNT = "/modify";
    public static final String VIEW_SUMMARY = "/summary";

    private AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * create new account for specific customer
     */
    @PostMapping(CREATE_ACCOUNT)
    public ResponseEntity<AccountDto> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.create(account));
    }

    /**
     * updating account for specific customer
     */
    @PostMapping(MODIFY_ACCOUNT)
    public ResponseEntity<AccountDto> modifyAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.modify(account));
    }

    /**
     * @return number of current balance from Customer Bank Account
     */
    @GetMapping(VIEW_SUMMARY)
    public ResponseEntity<BigDecimal> getBalanceSummary(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(accountService.viewSummary(id));
    }

    /**
     * remove one account from customer
     */
    @DeleteMapping()
    public ResponseEntity<String> deleteAccount(@RequestParam(value = "id") Long id) {
        accountService.remove(id);
        return ResponseEntity.ok("Account with ID " + id + " successfully deleted.");
    }

}
