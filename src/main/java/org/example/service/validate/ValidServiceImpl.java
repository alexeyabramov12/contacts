package org.example.service.validate;

import org.example.dto.ContactDto;
import org.example.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ValidServiceImpl implements ValidateService {

    private final String WRONG_LENGTH_MESSAGE = "Некорректный формат ввода данных";


    @Override
    public ContactDto validToAdd(String input) throws ValidationException {
        if (input == null) {
            throw new ValidationException(WRONG_LENGTH_MESSAGE);
        }

        String[] data = input.strip().split(";\\s");
        if (data.length < 3) {
            throw new ValidationException(WRONG_LENGTH_MESSAGE);
        }
        return new ContactDto(validName(data[0]), validNumber(data[1]), validEmail(data[2]));
    }

    @Override
    public String validToDelete(String input) throws ValidationException {
        if (input == null) {
            throw new ValidationException(WRONG_LENGTH_MESSAGE);
        }

        return validEmail(input.strip());
    }

    private String validName(String name) {
        String nameRegex = "^([А-Я][а-я]*(\\s)[А-Я][а-я]*(\\s)[А-Я][А-я]*$)";
        if (!name.matches(nameRegex)) {
            throw new ValidationException("Не правильный формат ввоода имени");
        }
        return name;
    }

    private String validNumber(String number) {
        String numberRegex = "^((\\+7|\\+8|7|8)*(9)+([0-9]){7})$";
        if (!number.matches(numberRegex)) {
            throw new ValidationException("Не правильный формат ввоода телефона");
        }
        return number;
    }

    private String validEmail(String email) {
        String emailRegex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        if (!email.matches(emailRegex)) {
            throw new ValidationException("Не правильный формат ввоода email");
        }
        return email;
    }
}
