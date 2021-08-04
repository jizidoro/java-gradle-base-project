package com.comrades.application.services.busline.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BusLineDto {
    public Integer id;

    @NotNull
    @NotEmpty
    public String codigo;

    @NotNull
    @NotEmpty
    public String nome;


}
