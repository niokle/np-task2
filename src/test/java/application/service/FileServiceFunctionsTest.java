package application.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceFunctionsTest {

    @Autowired
    FileServiceFunctions fileServiceFunctions;

    @Test
    public void isNumeric() {
        //given
        String trueString = "123456";
        String falseString = "1a2b3c4";

        //when
        boolean trueResult = fileServiceFunctions.isNumeric(trueString);
        boolean falseResult = fileServiceFunctions.isNumeric(falseString);

        //then
        Assert.assertTrue(trueResult);
        Assert.assertFalse(falseResult);
    }

    @Test
    public void checkPhoneNo() {
        //given
        String correctPhoneNo = "123456789";
        String incorrectPhoneNo1 = "12345";
        String incorrectPhoneNo2 = "12345678a";
        String incorrectPhoneNo3 = "1234567890";

        //when
        String correctResult = fileServiceFunctions.checkPhoneNo(correctPhoneNo);
        String incorrectResult1 = fileServiceFunctions.checkPhoneNo(incorrectPhoneNo1);
        String incorrectResult2 = fileServiceFunctions.checkPhoneNo(incorrectPhoneNo2);
        String incorrectResult3 = fileServiceFunctions.checkPhoneNo(incorrectPhoneNo3);

        //then
        Assert.assertEquals("", correctResult);
        Assert.assertEquals("Błąd w polu telefon. ", incorrectResult1);
        Assert.assertEquals("Błąd w polu telefon. ", incorrectResult2);
        Assert.assertEquals("Błąd w polu telefon. ", incorrectResult3);
    }

    @Test
    public void checkBirthDate() {
        //given
        String correctDate1 = "2019.01.01";
        String correctDate2 = "2019.5.6";
        String incorrectDate1 = "20190101";
        String incorrectDate2 = "abc.de.ef";
        String incorrectDate3 = "2019.30.01";

        //when
        String correctResult1 = fileServiceFunctions.checkBirthDate(correctDate1);
        String correctResult2 = fileServiceFunctions.checkBirthDate(correctDate2);
        String incorrectResult1 = fileServiceFunctions.checkBirthDate(incorrectDate1);
        String incorrectResult2 = fileServiceFunctions.checkBirthDate(incorrectDate2);
        String incorrectResult3 = fileServiceFunctions.checkBirthDate(incorrectDate3);

        //then
        Assert.assertEquals("", correctResult1);
        Assert.assertEquals("", correctResult2);
        Assert.assertEquals("Błąd w polu data urodzenia. ", incorrectResult1);
        Assert.assertEquals("Błąd w polu data urodzenia. ", incorrectResult2);
        Assert.assertEquals("Błąd w polu data urodzenia. ", incorrectResult3);
    }

    @Test
    public void checkFirstName() {
        //given
        String correctName = "abcd";
        String incorrectName1 = null;
        String incorrectName2 = "";

        //when
        String correctResult = fileServiceFunctions.checkFirstName(correctName);
        String incorrectResult1 = fileServiceFunctions.checkFirstName(incorrectName1);
        String incorrectResult2 = fileServiceFunctions.checkFirstName(incorrectName2);

        //then
        Assert.assertEquals("", correctResult);
        Assert.assertEquals("Błąd w polu imię. ", incorrectResult1);
        Assert.assertEquals("Błąd w polu imię. ", incorrectResult2);
    }

    @Test
    public void checkLastName() {
        //given
        String correctName = "abcd";
        String incorrectName1 = null;
        String incorrectName2 = "";

        //when
        String correctResult = fileServiceFunctions.checkLastName(correctName);
        String incorrectResult1 = fileServiceFunctions.checkLastName(incorrectName1);
        String incorrectResult2 = fileServiceFunctions.checkLastName(incorrectName2);

        //then
        Assert.assertEquals("", correctResult);
        Assert.assertEquals("Błąd w polu nazwisko. ", incorrectResult1);
        Assert.assertEquals("Błąd w polu nazwisko. ", incorrectResult2);
    }

    @Test
    public void birthDateConvert() {
        //given
        String date1 = "2019.02.03";
        String date2 = "1999.5.8";

        //when
        LocalDate resultDate1 = fileServiceFunctions.birthDateConvert(date1);
        LocalDate resultDate2 = fileServiceFunctions.birthDateConvert(date2);

        //then
        Assert.assertEquals(LocalDate.of(2019, 2, 3), resultDate1);
        Assert.assertEquals(LocalDate.of(1999, 5, 8), resultDate2);
    }
}