package com.comrades.application.services.busline.dtos;

import com.comrades.domain.models.BusLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusLineDto {
    public int id;
    public String codigo;
    public String nome;


}
