package org.example.service.contacts;

import lombok.RequiredArgsConstructor;
import org.example.dto.ContactDto;
import org.example.exception.ContactException;
import org.example.mapper.ContactMapper;
import org.example.model.Contact;
import org.example.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    @Override
    public ContactDto addContact(ContactDto dto) {
        return mapper.entityToDto(repository.addContact(mapper.dtoToEntity(dto)));
    }

    @Override
    public List<ContactDto> getAllContacts() {
        return repository.getAllContacts().stream().map(mapper::entityToDto).toList();
    }

    @Override
    public ContactDto deleteContactByEmail(String email) throws ContactException {
        Contact contact = repository.deleteContactByEmail(email);
        if (contact == null) {
            throw new ContactException(MessageFormat.format("Контакт с email - \"{0}\" отсутвует", email));
        }
        return mapper.entityToDto(contact);
    }

}
