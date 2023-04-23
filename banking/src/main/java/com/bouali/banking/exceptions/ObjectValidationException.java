package com.bouali.banking.exceptions;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ali Bouali
 * @since 15.09.22
 */

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

  @Getter
  private final Set<String> violations;

  @Getter
  private final String violationSource;

}
