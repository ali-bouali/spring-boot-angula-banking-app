package com.bouali.banking.services.impl;

import com.bouali.banking.dto.AccountDto;
import com.bouali.banking.exceptions.OperationNonPermittedException;
import com.bouali.banking.models.Account;
import com.bouali.banking.repositories.AccountRepository;
import com.bouali.banking.services.AccountService;
import com.bouali.banking.validators.ObjectsValidator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;
  private final ObjectsValidator<AccountDto> validator;

  @Override
  public Integer save(AccountDto dto) {
    // block account update -> iban cannot be changed
    /* if (dto.getId() != null) {
      throw new OperationNonPermittedException(
          "Account cannot be updated",
          "save account",
          "Account",
          "Update not permitted"
      );
    }*/
    validator.validate(dto);
    Account account = AccountDto.toEntity(dto);
    boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
    if (userHasAlreadyAnAccount && account.getUser().isActive()) {
      throw new OperationNonPermittedException(
          "the selected user has already an active account",
          "Create account",
          "Account service",
          "Account creation"
      );
    }
    // generate random IBAN when creating new account else do not update the IBAN
    if (dto.getId() == null) {
      account.setIban(generateRandomIban());
    }
    return repository.save(account).getId();
  }

  @Override
  public List<AccountDto> findAll() {
    return repository.findAll()
        .stream()
        .map(AccountDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public AccountDto findById(Integer id) {
    return repository.findById(id)
        .map(AccountDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : " + id));
  }

  @Override
  public void delete(Integer id) {
    // todo check delete account
    repository.deleteById(id);
  }

  private String generateRandomIban() {
    // generate an iban
    String iban = Iban.random(CountryCode.DE).toFormattedString();

    // check if the iban already exists
    boolean ibanExists = repository.findByIban(iban).isPresent();
    // if exists -> generate new random iban
    if (ibanExists) {
      generateRandomIban();
    }
    // if not exist -> return generated iban
    return iban;
  }
}
