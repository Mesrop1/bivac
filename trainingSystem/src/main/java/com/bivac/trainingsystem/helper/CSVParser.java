package com.bivac.trainingsystem.helper;

import com.bivac.trainingsystem.persistance.entity.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVParser {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public List<Student> parse(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));) {
            org.apache.commons.csv.CSVParser csvParser = new org.apache.commons.csv.CSVParser(fileReader,
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withIgnoreHeaderCase()
                            .withTrim()
                            .withDelimiter(';'));
            fileReader.readLine();
            List<Student> students = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Student student = new Student();
                student.setFirstName(csvRecord.get("\"firstName\""));
                student.setLastName(csvRecord.get("\"lastName\""));
                student.setAge(Integer.parseInt(csvRecord.get("\"age\"")));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
