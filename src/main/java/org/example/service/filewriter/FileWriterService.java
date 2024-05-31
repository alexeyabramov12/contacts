package org.example.service.filewriter;

import org.example.exception.FileWriterException;
import org.springframework.stereotype.Service;

@Service
public interface FileWriterService {

    void write() throws FileWriterException;
}
