package com.comrades.application;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Karanbir Singh on 07/23/2020
 */
public class TestDto {

    @NotEmpty
    private String message;

    @NotEmpty
    private String name;

    @NotNull
    private Integer age;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}