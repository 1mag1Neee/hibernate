package edu.imagine.entity.profile;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Embeddable
public class PersonalInfo {

    String firstname;
    String lastname;
    LocalDate birthDate;

}
