package com.bouali.banking.services;

import com.bouali.banking.dto.TransactionDto;
import java.util.List;

/**
 * @author Ali Bouali
 */
public interface TransactionService extends AbstractService<TransactionDto> {

  List<TransactionDto> findAllByUserId(Integer userId);
}
