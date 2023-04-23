package com.bouali.banking.services;

import com.bouali.banking.dto.ContactDto;
import java.util.List;

/**
 * @author Ali Bouali
 */
public interface ContactService extends AbstractService<ContactDto> {

  List<ContactDto> findAllByUserId(Integer userId);
}
