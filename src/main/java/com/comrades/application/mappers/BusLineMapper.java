package com.comrades.application.mappers;

import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.domain.models.BusLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BusLineMapper {

    BusLineMapper INSTANCE = Mappers.getMapper(BusLineMapper.class);

    BusLineDto busLineToBusLineDto(BusLine busLine);

    BusLine busLineDtoToBusLine(BusLineDto busLineDto);
}
