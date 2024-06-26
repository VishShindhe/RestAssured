package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private FavFood favFood;
    private List<String> jobs;

}
