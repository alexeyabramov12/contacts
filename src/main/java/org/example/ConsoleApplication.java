package org.example;

import lombok.RequiredArgsConstructor;
import org.example.dto.ContactDto;
import org.example.service.contacts.ContactServiceImpl;
import org.example.service.filewriter.FileWriterService;
import org.example.service.validate.ValidateService;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleApplication {

    private final ContactServiceImpl contactService;
    private final ValidateService validateService;
    private final FileWriterService fileWriterService;
    private boolean start = true;
    private final Scanner scanner = new Scanner(System.in);
    private static final String WRONG_COMMAND = "Недопустимая команда";

    public void start() {
        while (start) {
            printMainMassage();
            String input = scanner.nextLine().strip();
            try {
                switch (input) {
                    case "add" -> add();
                    case "list" -> list();
                    case "save" -> save();
                    case "delete" -> delete();
                    case "exit" -> start = false;
                    default -> System.out.println(WRONG_COMMAND);
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void printMainMassage() {
        System.out.println("Вас приветствует сервис по сохранению контактов");
        System.out.println("Нажмите одну из доступных команд:");
        System.out.println("add - Добавить новый контакт");
        System.out.println("list - Показать список всех контактов");
        System.out.println("delete - Удалить контакт по email");
        System.out.println("save - Сохранить в файл");
        System.out.println("exit - Завершить работу программы");
    }

    private void add() {
        System.out.println("Введите контакт в формате: Иванов Иван Иванович; +890999999; someEmail@example.example");
        String input = scanner.nextLine().strip();
        ContactDto contact = contactService.addContact(validateService.validToAdd(input));
        System.out.println(MessageFormat.format("Контакт: \"{0}; {1}; {2}\" добавлен успешно",contact.getFullName(),contact.getTelephoneNumber(), contact.getEmail()));
    }

    private void list() {
        List<ContactDto> contacts = contactService.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("Список контактов пуст");
        }

        System.out.println("Список всех контактов:");
        contacts.forEach(System.out::println);
    }

    private void save() {
        fileWriterService.write();
        System.out.println("Данный сохранены в файл в папке: \"data\"");
    }

    private void delete() {
        System.out.println("Веедите email:");
        String input = scanner.nextLine().strip();
        ContactDto contact = contactService.deleteContactByEmail(validateService.validToDelete(input));
        System.out.println(MessageFormat.format("Контакт: \"{0}; {1}; {2}\" удалён успешно",contact.getFullName(),contact.getTelephoneNumber(), contact.getEmail()));
    }
}
