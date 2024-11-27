package sk.zentity.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.zentity.model.dto.TransferDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(TransferRestController.TRANSFER)
public class TransferRestController {

    public static final String TRANSFER = "/transfer";
    public static final String TRANSFER_CREDIT = "/send";
    public static final String VIEW_HISTORY = "/history";
    public static final String SPECIFIC_TRANSITIONS = "/specific";

    private TransferService transferService;

    @Autowired
    public TransferRestController(TransferService transferService) {
        this.transferService = transferService;
    }


    /**
     *
     * @param creditorIban iban of customer which sending a money
     * @param debtorIban destinaiton where we want to send a money
     * @param amount how much money we want to send
     * @param message message for debtor
     * @return transfer summary info
     * @throws Exception if ibans is incorrect return error
     */
    @PostMapping(TRANSFER_CREDIT)
    public ResponseEntity<TransferDto> transferCredit(@RequestParam(value = "creditorIban") String creditorIban,
                                                      @RequestParam(value = "debtorIban") String debtorIban,
                                                      @RequestParam(value = "amount") BigDecimal amount,
                                                      @RequestParam(value = "message") String message) throws Exception {
        return ResponseEntity.ok(transferService.transferCredit(creditorIban, debtorIban, amount, message));
    }

    /**
     * @param id represent transitions from specific account
     * @return list of all transitions from specific customer
     */
    @GetMapping(VIEW_HISTORY)
    public ResponseEntity<List<TransferDto>> getViewHistory(@RequestParam(value = "account") Long id) {
        return ResponseEntity.ok(transferService.getTransitions(id));
    }

    /**
     *
     * @param amount is required and finding transition by amount
     * @param debtorIban is required and finding transition by user where we send a money
     * @param message is not required and finding transition by shortcut, which obtain from messages of transitions
     * @param accountId specific select by account
     * @return
     */
    @GetMapping(SPECIFIC_TRANSITIONS)
    public ResponseEntity<List<TransferDto>> getSpecificTransitions(@RequestParam(value = "amount") BigDecimal amount,
                                                                    @RequestParam(value = "debtorIban") String debtorIban,
                                                                    @RequestParam(value = "message", required = false, defaultValue = "") String message,
                                                                    @RequestParam(value = "accountId") Long accountId) {
        return ResponseEntity.ok(transferService.getSpecificTransitions(amount, debtorIban, message, accountId));
    }


}
