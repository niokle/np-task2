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

@Service
public class FileService {
    private final PersonService personService;
    private final RejectedService rejectedService;
    private final FileServiceFunctions fileServiceFunctions;

    @Autowired
    public FileService(PersonService personService, RejectedService rejectedService, FileServiceFunctions fileServiceFunctions) {
        this.personService = personService;
        this.rejectedService = rejectedService;
        this.fileServiceFunctions = fileServiceFunctions;
    }

    public void dataProcessing(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()) {
            String line = reader.readLine();
            saveData(fileName, line);
        }
    }

    private void saveData(String fileName, String line) {
        String[] record = line.split(";", 5);
        if (record.length == 4) {
            String message = fileServiceFunctions.checkFirstName(record[0]) + fileServiceFunctions.checkLastName(record[1])
                    + fileServiceFunctions.checkBirthDate(record[2]) + fileServiceFunctions.checkPhoneNo(record[3]);
            if (message.equals("")) {
                if (personService.getPersonsByPhoneNo(record[3]).size() == 0) {
                    personService.savePerson(new Person(record[0], record[1], fileServiceFunctions.birthDateConvert(record[2]), record[3]));
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
