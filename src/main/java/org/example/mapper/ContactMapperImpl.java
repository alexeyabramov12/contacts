package org.example.mapper;

import org.example.dto.ContactDto;
import org.example.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapperImpl implements ContactMapper{


    @Override
    public ContactDto entityToDto(Contact contact) {
        return new ContactDto(contact.getFullName(), contact.getTelephoneNumber(), contact.getEmail());
    }

    @Override
    public Contact dtoToEntity(ContactDto dto) {
        return new Contact(dto.getFullName(), dto.getTelephoneNumber(), dto.getEmail());
    }
}
