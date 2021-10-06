package com.eleks.academy.pharmagator.student;

import lombok.*;

import java.time.LocalDate;

@Data
public class Student {
    private Long id;
    @NonNull
    private String name;
    private String email;
    @NonNull
    private LocalDate dob;
    @NonNull
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Student(String name, String email, LocalDate dob, Integer age) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }
}
