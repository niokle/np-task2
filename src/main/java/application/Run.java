package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Run {
    public static void main(String[] args) {
        //todo
        SpringApplication.run(Run.class, args);
        //String date = "2000.30.12";
        //String[] d = date.split(".");
        //LocalDate dd = null;
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
        //try {
        //    dd = LocalDate.parse(date, dateTimeFormatter);
        //} catch (DateTimeException e) {
        //    System.out.println(dd);
        //}
        //System.out.println(d[0]);
        //System.out.println(d[1]);
        //System.out.println(d[2]);
    }

}
