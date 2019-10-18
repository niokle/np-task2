package application.service;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    RejectedService rejectedService;

    @Test
    public void dataProcessing() throws IOException {
        //given
        String path = "/Users/michalkleszczewski/Documents/Development/Projects/JAVA/newspoint-task2/src/main/resources/input.csv";
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        FileService fileService = new FileService(personService, rejectedService);

        //when
        fileService.dataProcessing(multipartFile);

        //then
        Assert.assertEquals(3, personService.getAllPersonsSorted().size());
        Assert.assertEquals(7, rejectedService.getAllRejected().size());

        //cleanup
        personService.deletePerson(personService.getPersonsByPhoneNo("600700800").get(0).getId());
        personService.deletePerson(personService.getPersonsByPhoneNo("666000111").get(0).getId());
        personService.deletePerson(personService.getPersonsByPhoneNo("670540120").get(0).getId());
        rejectedService.deleteRejectedByFileName("input.csv");
    }
}