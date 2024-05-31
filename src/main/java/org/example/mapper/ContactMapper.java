package org.example.mapper;

import org.example.dto.ContactDto;
import org.example.model.Contact;
import org.springframework.stereotype.Component;

@Component
public interface ContactMapper {

    ContactDto entityToDto(Contact contact);

    Contact dtoToEntity(ContactDto dto);
}
