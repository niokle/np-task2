package application.service;

import application.domain.Person;
import application.domain.Rejected;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class FileService {
    private final PersonService personService;
    private final RejectedService rejectedService;
    private final FileServiceFunctions fileServiceFunctions;

    private static Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Autowired
    public FileService(PersonService personService, RejectedService rejectedService, FileServiceFunctions fileServiceFunctions) {
        this.personService = personService;
        this.rejectedService = rejectedService;
        this.fileServiceFunctions = fileServiceFunctions;
    }

    public void dataProcessing(MultipartFile multipartFile) throws IOException {
        LOGGER.info("rozpoczęcie przetwarzania pliku");
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()) {
            String line = reader.readLine();
            saveData(fileName, line);
        }
        LOGGER.info("zakończenie przetwarzania pliku");
    }

    private void saveData(String fileName, String line) {
        String[] record = line.split(";", 5);
        if (record.length == 4) {
            String message = fileServiceFunctions.checkFirstName(record[0]) + fileServiceFunctions.checkLastName(record[1])
                    + fileServiceFunctions.checkBirthDate(record[2]) + fileServiceFunctions.checkPhoneNo(record[3]);
            if (message.equals("")) {
                if (isPhoneNumberExists(record[3])) {
                    savePerson(record[0], record[1], record[2], record[3]);
                } else {
                    saveRejected(line, "Osoba z takim numerem telefonu już istnieje. ", fileName);
                }
            } else {
                saveRejected(line, message, fileName);
            }
        } else {
            saveRejected(line, "Nieprawidlowa ilosc kolumn. ", fileName);
        }
    }

    private void saveRejected(String record, String description, String fileName) {
        rejectedService.saveRejected(new Rejected(record, description, fileName));
        LOGGER.info("zapis do tabeli rejected (record // description // filename): " + record + " // " + description + " // " + fileName);
    }

    private boolean isPhoneNumberExists(String phoneNo) {
        return personService.getPersonsByPhoneNo(phoneNo).size() == 0;
    }

    private void savePerson(String firstName, String lastName, String birthDate, String phoneNo) {
        personService.savePerson(new Person(firstName, lastName, fileServiceFunctions.birthDateConvert(birthDate), phoneNo));
        LOGGER.info("zapis rekordu do tabeli persons (first name // last name // birth date // phone): " +
                firstName + " // " + lastName + " // " + birthDate + " // " + phoneNo);
    }
}
