package org.example.repository;

import org.example.model.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("data")
public class ContactRepositoryImplWithData implements ContactRepository {

    private final Map<String, Contact> data;
    private final String FILE_PATH = "src/main/resources/default-contacts.txt";
    private final String ERROR_MASSAGE = "Wrong writing for default-contacts.txt";

    public ContactRepositoryImplWithData() {
        this.data = new HashMap<>();
        insertDataFromFile();
    }

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

    private void insertDataFromFile() {
        List<String> lines = null;
        try {
             lines = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(lines.isEmpty()) {
            throw new RuntimeException(ERROR_MASSAGE);
        }
            lines.forEach(l -> {
                String[] strings = l.split(";");
                String name = strings[0];
                String number = strings[1];
                String email = strings[2];
                Contact contact = new Contact(name, number, email);
                data.put(email, contact);
            });
    }
}
