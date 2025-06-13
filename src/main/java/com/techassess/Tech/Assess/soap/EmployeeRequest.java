package com.techassess.Tech.Assess.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EmployeeRequest", namespace = "http://techassess.com/employeesoap")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String position;
    private Double salary;
    private String birthDate;
    private String vinculationDate;

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getVinculationDate() {
        return vinculationDate;
    }
    public void setVinculationDate(String vinculationDate) {
        this.vinculationDate = vinculationDate;
    }
}