package org.example.service.validate;

import org.example.dto.ContactDto;
import org.example.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidServiceTest {

    private ContactDto contactDto1;
    private ValidateService service;

    @BeforeEach
    void setUp() {
        service = new ValidServiceImpl();

        contactDto1 = ContactDto.builder()
                .fullName("Иванов Иван Иванович")
                .telephoneNumber("+890999999")
                .email("someEmail@example.example")
                .build();
    }

    @Test
    @DisplayName("Test valid normal value")
    void givenValidString_whenValidToAdd_thenGetValidDto() {
        String value = "Иванов Иван Иванович; +890999999; someEmail@example.example";
        assertEquals(contactDto1, service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid when value is null")
    void givenNull_whenValidToAdd_thenGetValidDto() {
        assertThrows(ValidationException.class, () -> service.validToAdd(null));
    }

    @Test
    @DisplayName("Test valid normal value with spaces at the start and on the end")
    void givenStringWithSpacesStartAndEnd_whenValidToAdd_thenGetValidDto() {
        String value = " Иванов Иван Иванович; +890999999; someEmail@example.example ";
        assertEquals(contactDto1, service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with wrong name")
    void givenStringWithWrongName_whenValidToAdd_thenException() {
        String value = "Alex Иванов Иванович; +890999999; someEmail@example.example";
        assertThrows(ValidationException.class, () -> service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with wrong name")
    void givenStringWithEmptyName_whenValidToAdd_thenException() {
        String value = "+890999999; someEmail@example.example";
        assertThrows(ValidationException.class, () -> service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with empty phone")
    void givenStringWithWrongPhone_whenValidToAdd_thenException() {
        String value = "Иванов Иван Иванович; +89099999943; someEmail@example.example";
        assertThrows(ValidationException.class, () -> service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with empty phone")
    void givenStringWithEmptyPhone_whenValidToAdd_thenException() {
        String value = "Иванов Иван Иванович;someEmail@example.example";
        assertThrows(ValidationException.class, () -> service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with wrong email")
    void givenStringWithWrongEmail_whenValidToAdd_thenException() {
        String value = "Иванов Иван Иванович; +890999999; someEmailexample.example";
        assertThrows(ValidationException.class, () -> service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with empty email")
    void givenStringWithEmptyEmail_whenValidToAdd_thenException() {
        String value = "Иванов Иван Иванович; +89099999943";
        assertThrows(ValidationException.class, () -> service.validToAdd(value));
    }

    @Test
    @DisplayName("Test valid value with normal email")
    void givenStringWithNormalEmail_whenValidToDelete_thenGetValidEmail() {
        String email = "someEmail@example.example";
        assertEquals(email, service.validToDelete(email));
    }

    @Test
    @DisplayName("Test valid value with empty email")
    void givenStringWithEmptyEmail_whenValidToDelete_thenException() {
        String email = "";
        assertThrows(ValidationException.class, () -> service.validToDelete(email));
    }

    @Test
    @DisplayName("Test valid value when email is null")
    void givenStringWithNullEmail_whenValidToDelete_thenException() {
        assertThrows(ValidationException.class, () -> service.validToDelete(null));
    }

    @Test
    @DisplayName("Test valid value with wrong email")
    void givenStringWithWrongEmail_whenValidToDelete_thenException() {
        String email = "someEmailexample.example";
        assertThrows(ValidationException.class, () -> service.validToDelete(email));
    }
}