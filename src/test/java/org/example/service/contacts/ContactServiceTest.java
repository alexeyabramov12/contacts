package org.example.service.contacts;

import org.example.dto.ContactDto;
import org.example.exception.ContactException;
import org.example.mapper.ContactMapper;
import org.example.model.Contact;
import org.example.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {


    private ContactService service;
    @Mock
    private ContactRepository repository;
    @Mock
    private ContactMapper mapper;

    private Contact contact;
    private ContactDto dto;

    @BeforeEach
    void setUp() {
        contact = Contact.builder()
                .fullName("Иванов Иван Иванович")
                .telephoneNumber("+890999999")
                .email("someEmail@example.example")
                .build();
        dto = ContactDto.builder()
                .fullName("Иванов Иван Иванович")
                .telephoneNumber("+890999999")
                .email("someEmail@example.example")
                .build();
        service = new ContactServiceImpl(repository, mapper);
    }


    @Test
    @DisplayName("Test add dto with normal date then return dto")
    void givenNormalDto_whenAddOneContact_thenReturnDto() {
        when(mapper.dtoToEntity(dto)).thenReturn(contact);
        when(mapper.entityToDto(contact)).thenReturn(dto);

        when(repository.addContact(contact)).thenReturn(contact);

        assertEquals(dto, service.addContact(dto));
    }

    @Test
    @DisplayName("Test get data when data is present then return list with data")
    void givenOneData_whenGetAllContacts_thenReturnList() {
        when(mapper.entityToDto(contact)).thenReturn(dto);
        when(repository.getAllContacts()).thenReturn(List.of(contact));

        assertEquals(List.of(dto), service.getAllContacts());
    }

    @Test
    @DisplayName("Test get data when data is missing then exception")
    void givenNoData_whenGetAllContacts_thenException() {
        when(repository.getAllContacts()).thenReturn(new ArrayList<>());
        assertThrows(ContactException.class, () -> service.getAllContacts());
    }

    @Test
    @DisplayName("Test delete data when data is missing then exception")
    void givenNoData_whenDeleteContactByEmail_thenException() {
        String email = "someEmail@example.example";
        String expectMessage = MessageFormat.format("Контакт с email - \"{0}\" отсутвует", email);
        when(repository.deleteContactByEmail(email)).thenReturn(null);
        Exception exception = assertThrows(ContactException.class, () -> service.deleteContactByEmail(email));
        assertEquals(expectMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test delete data when data is present then return Dto")
    void givenData_whenDeleteContactByEmail_thenReturnDto() {
        String email = "someEmail@example.example";
        when(mapper.entityToDto(contact)).thenReturn(dto);
        when(repository.deleteContactByEmail(email)).thenReturn(contact);
        assertEquals(dto, service.deleteContactByEmail(email));
    }
}