package com.bouali.banking.dto;

import com.bouali.banking.models.Contact;
import com.bouali.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ali Bouali
 * @since 15.09.22
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactDto {

  private Integer id;

  private String firstname;

  private String lastname;

  private String email;

  private String iban;

  private Integer userId;

  public static ContactDto fromEntity(Contact contact) {
    return ContactDto.builder()
        .id(contact.getId())
        .firstname(contact.getFirstname())
        .lastname(contact.getLastname())
        .email(contact.getEmail())
        .iban(contact.getIban())
        .userId(contact.getUser().getId())
        .build();

  }

  public static Contact toEntity(ContactDto contact) {
    return Contact.builder()
        .id(contact.getId())
        .firstname(contact.getFirstname())
        .lastname(contact.getLastname())
        .email(contact.getEmail())
        .iban(contact.getIban())
        .user(
            User.builder()
                .id(contact.getUserId())
                .build()
        )
        .build();
  }

}
