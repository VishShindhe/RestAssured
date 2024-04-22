package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class FavFood {
    private String breakfast;
    private List<String> lunch;
    private String dinner;

}
