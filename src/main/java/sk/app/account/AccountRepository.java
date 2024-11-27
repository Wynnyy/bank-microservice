package sk.app.account;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.app.model.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIban(String iban);
}
