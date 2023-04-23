package com.bouali.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ali Bouali
 * @since 15.09.22
 */

@RequiredArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException {

  private final String errorMsg;

  private final String operationId;

  private final String source;

  private final String dependency;

}
