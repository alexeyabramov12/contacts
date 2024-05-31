package org.example.service.validate;

import org.example.dto.ContactDto;
import org.example.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public interface ValidateService {

    ContactDto validToAdd(String input) throws ValidationException;

    String validToDelete(String input) throws ValidationException;
}
