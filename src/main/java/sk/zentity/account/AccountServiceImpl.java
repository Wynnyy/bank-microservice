package sk.zentity.account;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sk.zentity.exception.BusinessException;
import sk.zentity.model.dto.AccountDto;
import sk.zentity.model.entities.Account;
import sk.zentity.model.mapper.UniMapper;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private UniMapper uniMapper;
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(UniMapper uniMapper, AccountRepository accountRepository) {
        this.uniMapper = uniMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto create(Account newAccount) {
        Account account = accountRepository.save(newAccount);
        log.info("Creating new account with id: [{}]", account.getId());
        return uniMapper.toDto(account);
    }

    @Override
    @Transactional
    public AccountDto modify(Account account) {
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new BusinessException("Account doesn't exist with id: " + account.getId(), HttpStatus.NOT_FOUND));

        account.setTransfer(account.getTransfer());
        accountRepository.save(account);
        log.info("Updating customer with id: [{}]", existingAccount.getId());
        return uniMapper.toDto(account);
    }

    @Override
    public void remove(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public BigDecimal viewSummary(Long id) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Account doesn't exist with id: " + id, HttpStatus.NOT_FOUND));
        return existingAccount.getBalance();
    }
}
