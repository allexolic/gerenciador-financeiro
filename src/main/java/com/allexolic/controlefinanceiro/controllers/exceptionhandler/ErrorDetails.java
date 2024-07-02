package com.allexolic.controlefinanceiro.controllers.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private int statuscode;
    private Date timestamp;
    private String message;
    private String description;
}
