package org.example.repository;

import org.example.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository {

    Contact addContact(Contact contact);

    List<Contact> getAllContacts();

    Contact deleteContactByEmail(String email);
}
