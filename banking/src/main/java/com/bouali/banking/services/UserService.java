package com.bouali.banking.services;

import com.bouali.banking.dto.AuthenticationRequest;
import com.bouali.banking.dto.AuthenticationResponse;
import com.bouali.banking.dto.LightUserDto;
import com.bouali.banking.dto.UserDto;

/**
 * @author Ali Bouali
 */
public interface UserService extends AbstractService<UserDto> {

  Integer validateAccount(Integer id);

  Integer invalidateAccount(Integer id);

  AuthenticationResponse register(UserDto user);

  AuthenticationResponse authenticate(AuthenticationRequest request);

  Integer update(LightUserDto userDto);
}
