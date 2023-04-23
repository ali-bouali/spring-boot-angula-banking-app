package com.bouali.banking.services;

import java.util.List;

/**
 * @author Ali Bouali
 */
public interface AbstractService<T> {

  Integer save(T dto);

  List<T> findAll();

  T findById(Integer id);

  void delete(Integer id);

}
