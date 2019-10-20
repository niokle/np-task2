package application.service;

import application.domain.Rejected;
import application.exception.RejectedNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RejectedServiceTest {

    @Autowired
    RejectedService rejectedService;

    @Test
    public void getRejected() throws RejectedNotFoundException {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected = rejectedService.saveRejected(rejected1);

        //when
        Rejected resultRejected = rejectedService.getRejected(rejected.getId()).get();

        //then
        Assert.assertEquals("record1", resultRejected.getRecord());
        Assert.assertEquals("description1", resultRejected.getDescription());
        Assert.assertEquals("file1", resultRejected.getFileName());

        //cleanup
        rejectedService.deleteRejected(resultRejected.getId());
    }

    @Test
    public void saveRejected() throws RejectedNotFoundException {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file3");

        //when
        Rejected resultRejected1 = rejectedService.saveRejected(rejected1);
        Rejected resultRejected2 = rejectedService.saveRejected(rejected2);
        Rejected resultRejected3 = rejectedService.saveRejected(rejected3);

        //then
        Assert.assertEquals("record1", resultRejected1.getRecord());
        Assert.assertEquals("description1", resultRejected1.getDescription());
        Assert.assertEquals("file1", resultRejected1.getFileName());
        Assert.assertEquals("record2", resultRejected2.getRecord());
        Assert.assertEquals("description2", resultRejected2.getDescription());
        Assert.assertEquals("file2", resultRejected2.getFileName());
        Assert.assertEquals("record3", resultRejected3.getRecord());
        Assert.assertEquals("description3", resultRejected3.getDescription());
        Assert.assertEquals("file3", resultRejected3.getFileName());

        //cleanup
        rejectedService.deleteRejected(resultRejected1.getId());
        rejectedService.deleteRejected(resultRejected2.getId());
        rejectedService.deleteRejected(resultRejected3.getId());
    }

    @Test
    public void getAllRejected() throws RejectedNotFoundException {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file3");

        //when
        int beforeTest = rejectedService.getAllRejected().size();
        Rejected resultRejected1 = rejectedService.saveRejected(rejected1);
        Rejected resultRejected2 = rejectedService.saveRejected(rejected2);
        Rejected resultRejected3 = rejectedService.saveRejected(rejected3);
        int afterTest = rejectedService.getAllRejected().size();
        int result = afterTest - beforeTest;

        //then
        Assert.assertEquals(3, result);

        //cleanup
        rejectedService.deleteRejected(resultRejected1.getId());
        rejectedService.deleteRejected(resultRejected2.getId());
        rejectedService.deleteRejected(resultRejected3.getId());
    }

    @Test
    public void deleteRejected() {
    }

    @Test
    public void deleteAllRejected() {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file3");
        Rejected resultRejected1 = rejectedService.saveRejected(rejected1);
        Rejected resultRejected2 = rejectedService.saveRejected(rejected2);
        Rejected resultRejected3 = rejectedService.saveRejected(rejected3);

        //when
        rejectedService.deleteAllRejected();

        //then
        Assert.assertEquals(0, rejectedService.getAllRejected().size());
    }

    @Test
    public void deleteRejectedByFileName() throws RejectedNotFoundException {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file2");
        Rejected resultRejected1 = rejectedService.saveRejected(rejected1);
        Rejected resultRejected2 = rejectedService.saveRejected(rejected2);
        Rejected resultRejected3 = rejectedService.saveRejected(rejected3);

        //when
        int beforeTest = rejectedService.getAllRejected().size();
        rejectedService.deleteRejectedByFileName("file2");
        int afterTest = rejectedService.getAllRejected().size();
        int result = beforeTest - afterTest;

        //then
        Assert.assertEquals(2, result);

        //cleanup
        rejectedService.deleteRejected(rejected1.getId());
    }
}