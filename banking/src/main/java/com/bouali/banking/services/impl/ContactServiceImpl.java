package com.bouali.banking.services.impl;

import com.bouali.banking.dto.ContactDto;
import com.bouali.banking.models.Contact;
import com.bouali.banking.repositories.ContactRepository;
import com.bouali.banking.services.ContactService;
import com.bouali.banking.validators.ObjectsValidator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactRepository repository;
  private final ObjectsValidator<ContactDto> validator;

  @Override
  public Integer save(ContactDto dto) {
    validator.validate(dto);
    Contact contact = ContactDto.toEntity(dto);
    return repository.save(contact).getId();
  }

  @Override
  public List<ContactDto> findAll() {
    return repository.findAll()
        .stream()
        .map(ContactDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public ContactDto findById(Integer id) {
    return repository.findById(id)
        .map(ContactDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException("No contact was found with the ID :" + id));
  }

  @Override
  public void delete(Integer id) {
    // todo check delete
    repository.deleteById(id);
  }

  @Override
  public List<ContactDto> findAllByUserId(Integer userId) {
    return repository.findAllByUserId(userId)
        .stream()
        .map(ContactDto::fromEntity)
        .collect(Collectors.toList());
  }
}
