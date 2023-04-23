package com.bouali.banking.dto;

import com.bouali.banking.models.Transaction;
import com.bouali.banking.models.TransactionType;
import com.bouali.banking.models.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
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
public class TransactionDto {

  private Integer id;

  @Positive
  private BigDecimal amount;

  private TransactionType type;

  private String destinationIban;

  private LocalDate transactionDate;

  private Integer userId;

  public static TransactionDto fromEntity(Transaction transaction) {
    return TransactionDto.builder()
        .id(transaction.getId())
        .amount(transaction.getAmount())
        .type(transaction.getType())
        .transactionDate(transaction.getTransactionDate())
        .destinationIban(transaction.getDestinationIban())
        .userId(transaction.getUser().getId())
        .build();
  }

  public static Transaction toEntity(TransactionDto transaction) {
    return Transaction.builder()
        .id(transaction.getId())
        .amount(transaction.getAmount())
        .type(transaction.getType())
        .transactionDate(LocalDate.now())
        .destinationIban(transaction.getDestinationIban())
        .user(
            User.builder()
                .id(transaction.getUserId())
                .build()
            )
        .build();
  }

}
