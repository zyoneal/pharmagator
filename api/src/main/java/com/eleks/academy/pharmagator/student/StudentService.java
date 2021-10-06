package com.eleks.academy.pharmagator.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudent() {
        return List.of(new Student(
                1L,
                "Senya",
                "senya@mail.ru",
                LocalDate.of(2009, Month.FEBRUARY, 11),
                9));
    }
}
