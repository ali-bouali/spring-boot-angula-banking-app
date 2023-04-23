package com.bouali.banking.repositories;

import com.bouali.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Bouali
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
