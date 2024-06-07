package org.example.service.filewriter;

import org.example.dto.ContactDto;
import org.example.exception.FileWriterException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileWriterService {

    boolean write(List<ContactDto> contacts) throws FileWriterException;
}
