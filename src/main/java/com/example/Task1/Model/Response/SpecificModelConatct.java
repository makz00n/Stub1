package com.example.Task1.Model.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SpecificModelConatct {
    private String name;
    private List<String> telecom;
}
