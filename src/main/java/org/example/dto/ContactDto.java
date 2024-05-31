package org.example.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private String fullName;
    private String telephoneNumber;
    private String email;

    @Override
    public String toString() {
        return fullName +
                ";" + telephoneNumber +
                ";" + email;
    }
}
