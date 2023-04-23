package com.bouali.banking.repositories;

import com.bouali.banking.models.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Bouali
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

  Optional<Account> findByIban(String iban);

  Optional<Account> findByUserId(Integer id);
}
