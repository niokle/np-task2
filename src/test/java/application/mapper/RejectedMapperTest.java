package application.mapper;

import application.domain.Rejected;
import application.dto.RejectedDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RejectedMapperTest {

    @Autowired
    RejectedMapper rejectedMapper;

    @Test
    public void rejectedToRejectedDto() {
        //given
        Rejected rejected = new Rejected(1L, "record", "description", "file");

        //when
        RejectedDto rejectedDto = rejectedMapper.rejectedToRejectedDto(rejected);
        Long rejectedDtoId = rejectedDto.getId();

        //then
        Assert.assertEquals("record", rejectedDto.getRecord());
        Assert.assertEquals("description", rejectedDto.getDescription());
        Assert.assertEquals("file", rejectedDto.getFileName());
    }

    @Test
    public void rejectedsToRejectedsDtos() {
        //given
        Rejected rejected1 = new Rejected(1L, "record1", "desc1", "file1");
        Rejected rejected2 = new Rejected(2L, "record2", "desc2", "file2");
        List<Rejected> rejecteds = new ArrayList<>();
        rejecteds.add(rejected1);
        rejecteds.add(rejected2);

        //when
        List<RejectedDto> rejectedDtos = rejectedMapper.rejectedsToRejectedsDtos(rejecteds);

        //then
        Assert.assertEquals(2, rejectedDtos.size());
    }
}