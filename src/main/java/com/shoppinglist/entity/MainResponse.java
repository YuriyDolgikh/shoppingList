package com.shoppinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@ToString
public class MainResponse <T>{
    private HttpStatus status;
    private String message;
    private T responseObject;
}
