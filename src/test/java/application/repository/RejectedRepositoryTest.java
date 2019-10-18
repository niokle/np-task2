package application.repository;

import application.domain.Rejected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RejectedRepositoryTest {

    @Autowired
    RejectedRepository rejectedRepository;

    @Test
    public void save() {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file3");

        //when
        Rejected resultRejected1 = rejectedRepository.save(rejected1);
        Rejected resultRejected2 = rejectedRepository.save(rejected2);
        Rejected resultRejected3 = rejectedRepository.save(rejected3);

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
        rejectedRepository.delete(resultRejected1);
        rejectedRepository.delete(resultRejected2);
        rejectedRepository.delete(resultRejected3);
    }

    @Test
    public void findAll() {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file3");

        //when
        int beforeTest = rejectedRepository.findAll().size();
        Rejected resultRejected1 = rejectedRepository.save(rejected1);
        Rejected resultRejected2 = rejectedRepository.save(rejected2);
        Rejected resultRejected3 = rejectedRepository.save(rejected3);
        int afterTest = rejectedRepository.findAll().size();
        int result = afterTest - beforeTest;

        //then
        Assert.assertEquals(3, result);

        //cleanup
        rejectedRepository.delete(resultRejected1);
        rejectedRepository.delete(resultRejected2);
        rejectedRepository.delete(resultRejected3);
    }

    @Test
    public void findById() {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");

        //when
        Rejected rejected = rejectedRepository.save(rejected1);
        Rejected resultRejected = rejectedRepository.findById(rejected.getId()).get();

        //then
        Assert.assertEquals("record1", resultRejected.getRecord());
        Assert.assertEquals("description1", resultRejected.getDescription());
        Assert.assertEquals("file1", resultRejected.getFileName());

        //cleanup
        rejectedRepository.delete(resultRejected);
    }

    @Test
    public void findByFileName() {
        //given
        Rejected rejected1 = new Rejected("record1", "description1", "file1");
        Rejected rejected2 = new Rejected("record2", "description2", "file2");
        Rejected rejected3 = new Rejected("record3", "description3", "file2");

        //when
        Rejected resultRejected1 = rejectedRepository.save(rejected1);
        Rejected resultRejected2 = rejectedRepository.save(rejected2);
        Rejected resultRejected3 = rejectedRepository.save(rejected3);
        List<Rejected> results = rejectedRepository.findByFileName("file2");

        //then
        Assert.assertEquals(2, results.size());

        //cleanup
        rejectedRepository.delete(resultRejected1);
        rejectedRepository.delete(resultRejected2);
        rejectedRepository.delete(resultRejected3);
    }
}