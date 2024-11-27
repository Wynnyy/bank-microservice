package sk.zentity.account;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.zentity.model.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIban(String iban);
}
