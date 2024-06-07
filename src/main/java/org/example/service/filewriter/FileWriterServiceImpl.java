package org.example.service.filewriter;

import lombok.RequiredArgsConstructor;
import org.example.dto.ContactDto;
import org.example.exception.FileWriterException;
import org.example.model.Contact;
import org.example.service.contacts.ContactService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileWriterServiceImpl implements FileWriterService {

    private final String FILE_PATH = "data/file.txt";

    @Override
    public boolean write(List<ContactDto> contacts) throws FileWriterException{
        if (contacts == null || contacts.isEmpty()) {
            throw new FileWriterException("Списов контактов пуст");
        }

        List<String> data = contacts.stream().map(ContactDto::toString).toList();


        try {
            Files.write(Path.of("data/file.txt"), data, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            throw new FileWriterException("Ошибка в записи файла");
        }
    }

}
