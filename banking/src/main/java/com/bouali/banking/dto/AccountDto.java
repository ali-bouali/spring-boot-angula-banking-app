package com.bouali.banking.dto;

import com.bouali.banking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ali Bouali
 * @since 15.09.22
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {

  private Integer id;

  private String iban;

  private UserDto user;

  public static AccountDto fromEntity(Account account) {
    return AccountDto.builder()
        .id(account.getId())
        .iban(account.getIban())
        .user(UserDto.fromEntity(account.getUser()))
        .build();

  }

  public static Account toEntity(AccountDto account) {
    return Account.builder()
        .id(account.getId())
        .iban(account.getIban())
        .user(UserDto.toEntity(account.getUser()))
        .build();

  }
}
