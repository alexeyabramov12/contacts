package org.example.dto;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDto that = (ContactDto) o;
        return Objects.equals(fullName, that.fullName) && Objects.equals(telephoneNumber, that.telephoneNumber) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, telephoneNumber, email);
    }
}
