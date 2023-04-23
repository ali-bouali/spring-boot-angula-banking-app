package com.bouali.banking.services.impl;

import com.bouali.banking.config.JwtUtils;
import com.bouali.banking.dto.AccountDto;
import com.bouali.banking.dto.AuthenticationRequest;
import com.bouali.banking.dto.AuthenticationResponse;
import com.bouali.banking.dto.LightUserDto;
import com.bouali.banking.dto.UserDto;
import com.bouali.banking.models.Account;
import com.bouali.banking.models.Role;
import com.bouali.banking.models.User;
import com.bouali.banking.repositories.RoleRepository;
import com.bouali.banking.repositories.UserRepository;
import com.bouali.banking.services.AccountService;
import com.bouali.banking.services.UserService;
import com.bouali.banking.validators.ObjectsValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String ROLE_USER = "ROLE_USER";
  private final UserRepository repository;
  private final AccountService accountService;
  private final ObjectsValidator<UserDto> validator;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authManager;
  private final RoleRepository roleRepository;

  @Override
  public Integer save(UserDto dto) {
    validator.validate(dto);
    User user = UserDto.toEntity(dto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return repository.save(user).getId();
  }



  @Override
  @Transactional
  public List<UserDto> findAll() {
    return repository.findAll()
        .stream()
        .map(UserDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto findById(Integer id) {
    return repository.findById(id)
        .map(UserDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
  }

  @Override
  public void delete(Integer id) {
    // todo check before delete
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public Integer validateAccount(Integer id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

    if (user.getAccount() == null) {
      // create a bank account
      AccountDto account = AccountDto.builder()
          .user(UserDto.fromEntity(user))
          .build();
      var savedAccount = accountService.save(account);
      user.setAccount(
          Account.builder()
              .id(savedAccount)
              .build()
      );
    }

    user.setActive(true);
    repository.save(user);
    return user.getId();
  }

  @Override
  public Integer invalidateAccount(Integer id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

    user.setActive(false);
    repository.save(user);
    return user.getId();
  }

  @Override
  @Transactional
  public AuthenticationResponse register(UserDto dto) {
    validator.validate(dto);
    User user = UserDto.toEntity(dto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(
        findOrCreateRole(ROLE_USER)
    );
    var savedUser = repository.save(user);
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", savedUser.getId());
    claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
    String token = jwtUtils.generateToken(savedUser, claims);
    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    final User user = repository.findByEmail(request.getEmail()).get();
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());
    claims.put("fullName", user.getFirstname() + " " + user.getLastname());
    final String token = jwtUtils.generateToken(user, claims);
    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }

  @Override
  public Integer update(LightUserDto userDto) {
    User user = LightUserDto.toEntity(userDto);
    return repository.save(user).getId();
  }

  private Role findOrCreateRole(String roleName) {
    Role role = roleRepository.findByName(roleName)
        .orElse(null);
    if (role == null) {
      return roleRepository.save(
          Role.builder()
              .name(roleName)
              .build()
      );
    }
    return role;
  }
}
