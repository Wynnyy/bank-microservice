package sk.app.transfer;

import sk.app.model.dto.TransferDto;

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
