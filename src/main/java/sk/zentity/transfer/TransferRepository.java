package sk.zentity.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.zentity.model.entities.Account;
import sk.zentity.model.entities.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query(value = """
            SELECT *
                    FROM Transfer
                    WHERE
                         (account_id = :accountId) AND
                         (amount = :amount) AND
                         (debtor_iban = :debtorIban) AND
                         (message LIKE CONCAT('%',:message,'%'));
                           """, nativeQuery = true)
    List<Transfer> getSpecificTransfers(@Param("amount") BigDecimal amount,
                                        @Param("debtorIban") String debtorIban,
                                        @Param("message") String message,
                                        @Param("accountId") Long accountId);

    List<Transfer> findAllByAccountId(Long id);
}
