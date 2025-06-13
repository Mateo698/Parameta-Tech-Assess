package com.techassess.Tech.Assess.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "EmployeeResponse", namespace = "http://techassess.com/employeesoap")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeResponse {
    private String message;

    // Getters and Setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}