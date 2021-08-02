package com.comrades.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TestDto {

    @NotEmpty
    private String message;

    @NotEmpty
    private String name;

    @NotNull
    private Integer age;
}