package application.service;

import org.springframework.stereotype.Component;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.util.regex.Pattern.matches;

@Component
public class FileServiceFunctions {

    public boolean isNumeric(String string) {
        return matches("[0-9]+", string);
    }

    public String checkPhoneNo(String phoneNo) {
        if (phoneNo.length() == 9 && isNumeric(phoneNo)) {
            return "";
        }
        return "Błąd w polu telefon. ";
    }

    public String checkBirthDate(String birthDate) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");
            LocalDate localDate = LocalDate.parse(birthDate, dateTimeFormatter);
        } catch (DateTimeException e) {
            return "Błąd w polu data urodzenia. ";
        }
        return "";
    }

    public String checkFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) {
            return "Błąd w polu imię. ";
        }
        return "";
    }

    public String checkLastName(String lastName) {
        if (lastName == null || lastName.length() == 0) {
            return "Błąd w polu nazwisko. ";
        }
        return "";
    }

    public LocalDate birthDateConvert(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");
        return LocalDate.parse(string, dateTimeFormatter);
    }
}
