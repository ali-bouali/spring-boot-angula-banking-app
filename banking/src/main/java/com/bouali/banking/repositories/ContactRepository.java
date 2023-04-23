package com.bouali.banking.repositories;

import com.bouali.banking.models.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Bouali
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

  List<Contact> findAllByUserId(Integer userId);
}
