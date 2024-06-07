package org.example.service.contacts;

import org.example.dto.ContactDto;
import org.example.exception.ContactException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    ContactDto addContact(ContactDto dto);

    List<ContactDto> getAllContacts() throws ContactException;

    ContactDto deleteContactByEmail(String email) throws ContactException;

}
