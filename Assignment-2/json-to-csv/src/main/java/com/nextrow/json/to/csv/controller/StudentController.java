package com.nextrow.json.to.csv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextrow.json.to.csv.entity.Student;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/api")
    public String getJsonFile() throws IOException {

        File f = new File("C:/Users/ruchi/OneDrive/Desktop/Assignment/Assignment-2/student-json-files");
        File[] files = f.listFiles();

        System.out.println(Arrays.stream(files).toList());

        File file = new File("student.csv");
        Writer writer = new FileWriter(file);


        for (File i : files) {

            String fname = i.getName();
            System.out.println(fname);
            try {

                Resource resource = resourceLoader.getResource("file:"+i.getAbsolutePath());
                InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
                String json = FileCopyUtils.copyToString(inputStreamReader);

                ObjectMapper objectMapper = new ObjectMapper();

                Student student = objectMapper.readValue(json, Student.class);

                System.out.println(student.getMarks());

                ColumnPositionMappingStrategy<Student> positionMappingStrategy = new ColumnPositionMappingStrategy<>();
                positionMappingStrategy.setType(Student.class);

                String[] columns = {"firstname", "lastname", "branch", "address", "marks","totalmarks","percentage"};

                positionMappingStrategy.setColumnMapping(columns);


                StatefulBeanToCsvBuilder<Student> beanToCsv = new StatefulBeanToCsvBuilder<Student>(writer);

                StatefulBeanToCsv<Student> beanWriter = beanToCsv
                        .withOrderedResults(true)  // Ensure the columns are written in the specified order
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withMappingStrategy(positionMappingStrategy).build();

                int totalmarks= getTotalMarks(student.getMarks());
                student.setTotalmarks(totalmarks);

/*
                double averagemarks= getAverageMarks(student.getMarks());
                student.setTotalmarks(totalmarks);
*/

                double percentage= getPercentage(student.getMarks());

                student.setTotalmarks(totalmarks);
//                student.setAveragemarks(averagemarks);
                student.setPercentage(percentage);

                List<Student> studentList = List.of(student);

                System.out.println("write operation");
                beanWriter.write(studentList);

                writer.append('\n');
                System.out.println("One operation is done: "+fname);

            } catch (Exception e) {
                return "Exception " + e + " has arrived";
            }
        }

        writer.close();

        return "Done Boss!!";
    }

    private double getPercentage(HashMap<String, Integer> marks) {
        double curr=0.0;
        int c=0;
        for (int e:marks.values()){
            c++;
            curr+=e;
        }
        c=c*100;
        return (curr*100)/c;
    }

/*    private double getAverageMarks(HashMap<String, Integer> marks) {
        double average=0;
        int c=0;
        for (int e:marks.values()){
            c++;
            average+=e;
        }
        return average/c;
    }*/

    private int getTotalMarks(HashMap<String, Integer> marks) {
        int total=0;
        for (int e:marks.values()){
            total+=e;
        }
        return total;
    }

}
