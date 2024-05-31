package org.example.repository;

import org.example.model.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("default")
public class ContactRepositoryImpl implements ContactRepository {

    private final Map<String, Contact> data = new HashMap<>();

    @Override
    public Contact addContact(Contact contact) {
        data.put(contact.getEmail(), contact);
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Contact deleteContactByEmail(String email) {
        return data.remove(email);
    }
}
