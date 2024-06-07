package org.example.service.filewriter;

import org.example.dto.ContactDto;
import org.example.exception.FileWriterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileWriterServiceTest {


    private FileWriterService service;

    @BeforeEach
    void setUp() {
        service = new FileWriterServiceImpl();
    }

    @Test
    @DisplayName("Test write file with normal data")
    void givenNormalData_whenWrite_thenTrue() {
        List<ContactDto> data = List.of(ContactDto.builder()
                .fullName("Иванов Иван Иванович")
                .telephoneNumber("+890999999")
                .email("someEmail@example.example")
                .build(), ContactDto.builder()
                .fullName("Сидоров Иван Иванович")
                .telephoneNumber("+890999994")
                .email("someEmail@example.com")
                .build());
        assertTrue(service.write(data));
    }

    @Test
    @DisplayName("Test write file with data is null")
    void givenNull_whenWrite_thenException() {
        assertThrows(FileWriterException.class, () -> service.write(null));
    }

    @Test
    @DisplayName("Test write file with empty data")
    void givenEmptyData_whenWrite_thenException() {
        List<ContactDto> data = new ArrayList<>();
        assertThrows(FileWriterException.class, () -> service.write(data));
    }
}