package sk.zentity.transfer;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sk.zentity.account.AccountRepository;
import sk.zentity.exception.BusinessException;
import sk.zentity.model.dto.TransferDto;
import sk.zentity.model.entities.Account;
import sk.zentity.model.entities.Transfer;
import sk.zentity.model.mapper.UniMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

    private UniMapper uniMapper;
    private TransferRepository transferRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TransferServiceImpl(UniMapper uniMapper, TransferRepository transferRepository, AccountRepository accountRepository) {
        this.uniMapper = uniMapper;
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * transfer credits from one bank account to another
     */
    @Override
    @Transactional
    public TransferDto transferCredit(String creditorIban, String debtorIban, BigDecimal amount, String message) throws Exception {
        Account fromAccount = accountRepository.findByIban(creditorIban);
        Account toAccount = accountRepository.findByIban(debtorIban);

        accountValidator(fromAccount, toAccount);
        transferCalculator(fromAccount, toAccount, amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return uniMapper.toDto(transferRepository.save(initTransfer(creditorIban, debtorIban, amount, message, fromAccount.getId())));
    }

    /**
     * Method calculate and subtract money from bank account and add to 'debtorIban' account
     *
     * @throws Exception if balance is not bigger than amount, throw exception
     */
    public void transferCalculator(Account fromAccount, Account toAccount, BigDecimal amount) throws Exception {
        if (fromAccount.getBalance().compareTo(amount) > 0) {
            BigDecimal amountAfterTransferCreditor = fromAccount.getBalance().subtract(amount);
            fromAccount.setBalance(amountAfterTransferCreditor);

            BigDecimal amountAfterTransferDebtor = toAccount.getBalance().add(amount);
            toAccount.setBalance(amountAfterTransferDebtor);
        } else {
            throw new BusinessException("You don't have enough money in bank account for realize this transfer", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * @param fromAccount if it's null, then is problem on FE side, variable wasn't correctly filled, throw exception for user to contact support
     * @param toAccount   account where we send a money, exception if user type incorrect iban
     */

    public void accountValidator(Account fromAccount, Account toAccount) throws Exception {
        if (fromAccount == null) {
            throw new BusinessException("We're Having Trouble with this Transaction, problem is on server side, please contact support", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (toAccount == null) {
            throw new BusinessException("Account of the recipient doesn't exist , please type correct IBAN", HttpStatus.NOT_FOUND);
        }
    }

    public Transfer initTransfer(String creditorIban, String debtorIban, BigDecimal amount, String message, Long accountId) {
        return new Transfer(null,
                LocalDateTime.now(),
                amount,
                debtorIban,
                creditorIban,
                message,
                accountId
        );
    }

    @Override
    public List<TransferDto> getTransitions(Long accountId) {
        List<Transfer> transfer = transferRepository.findAllByAccountId(accountId);
        return uniMapper.toDtoListTransfers(transfer);
    }


    @Override
    public List<TransferDto> getSpecificTransitions(BigDecimal amount, String debtorIban, String message, Long accountId) {
        return uniMapper.toDtoListTransfers(transferRepository.getSpecificTransfers(amount, debtorIban, message, accountId));
    }
}
