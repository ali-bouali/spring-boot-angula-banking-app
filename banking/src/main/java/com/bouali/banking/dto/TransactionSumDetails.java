package com.bouali.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Ali Bouali
 */
public interface TransactionSumDetails {

  LocalDate getTransactionDate();

  BigDecimal getAmount();

}
