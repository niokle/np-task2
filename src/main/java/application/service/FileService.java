package application.service;

import application.domain.Person;
import application.domain.Rejected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.util.regex.Pattern.matches;

@Service
public class FileService {
    private final PersonService personService;
    private final RejectedService rejectedService;

    @Autowired
    public FileService(PersonService personService, RejectedService rejectedService) {
        this.personService = personService;
        this.rejectedService = rejectedService;
    }
    //todo
    public void dataProcessing(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()) {
            String line = reader.readLine();
            String[] record = line.split(";", 5);
            if (record.length == 4) {
                String message = checkFirstName(record[0]) + checkLastName(record[1]) + checkBirthDate(record[2]) + checkPhoneNo(record[3]);
                if (message.equals("")) {
                    if (personService.getPersonsByPhoneNo(record[3]).size() == 0) {
                        personService.savePerson(new Person(record[0], record[1], birthDateConvert(record[2]), record[3]));
                    } else {
                        rejectedService.saveRejected(new Rejected(line, "Osoba z takim numerem telefonu już istnieje. ", fileName));
                    }
                } else {
                    rejectedService.saveRejected(new Rejected(line, message, fileName));
                }
            } else {
                rejectedService.saveRejected(new Rejected(line, "Nieprawidlowa ilosc kolumn. ", fileName));
            }
        }
    }

    private String checkFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) {
            return "Błąd w polu imię. ";
        }
        return "";
    }

    private String checkLastName(String lastName) {
        if (lastName == null || lastName.length() == 0) {
            return "Błąd w polu nazwisko. ";
        }
        return "";
    }

    private String checkBirthDate(String birthDate) {
        LocalDate localDate = null;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
            localDate = LocalDate.parse(birthDate, dateTimeFormatter);
        } catch (DateTimeException e) {
            return "Błąd w polu data urodzenia. ";
        }
        return "";
    }

    private String checkPhoneNo(String phoneNo) {
        if (phoneNo.length() != 9 && !isNumeric(phoneNo)) {
            return "Błąd w polu telefon. ";
        }
        return "";
    }

    private boolean isNumeric(String string) {
        return matches("d+", string);
    }

    private LocalDate birthDateConvert(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
        return LocalDate.parse(string, dateTimeFormatter);
    }
}
