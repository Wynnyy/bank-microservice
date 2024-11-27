package sk.zentity.transfer;

import sk.zentity.model.dto.TransferDto;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {

    TransferDto transferCredit(String creditorIban,
                               String debtorIban,
                               BigDecimal amount,
                               String message) throws Exception;

    List<TransferDto> getTransitions(Long accountId);

    List<TransferDto> getSpecificTransitions(BigDecimal amount, String debtorIban, String message, Long accountId);


}
