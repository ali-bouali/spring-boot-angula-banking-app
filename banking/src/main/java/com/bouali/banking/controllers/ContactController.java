package com.bouali.banking.controllers;

import com.bouali.banking.dto.ContactDto;
import com.bouali.banking.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Bouali
 */
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Tag(name = "contact")
public class ContactController {

  private final ContactService service;

  @PostMapping("/")
  public ResponseEntity<Integer> save(
      @RequestBody ContactDto contactDto
  ) {
    return ResponseEntity.ok(service.save(contactDto));
  }

  @GetMapping("/")
  public ResponseEntity<List<ContactDto>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{contact-id}")
  public ResponseEntity<ContactDto> findById(
      @PathVariable("contact-id") Integer contactId
  ) {
    return ResponseEntity.ok(service.findById(contactId));
  }

  @GetMapping("/users/{user-id}")
  public ResponseEntity<List<ContactDto>> findAllByUserId(
      @PathVariable("user-id") Integer userId
  ) {
    return ResponseEntity.ok(service.findAllByUserId(userId));
  }

  @DeleteMapping("/{contact-id}")
  public ResponseEntity<Void> delete(
      @PathVariable("contact-id") Integer contactId
  ) {
    service.delete(contactId);
    return ResponseEntity.accepted().build();
  }

}
