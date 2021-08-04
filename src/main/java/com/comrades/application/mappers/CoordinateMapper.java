package com.comrades.application.mappers;

import com.comrades.application.services.itinerary.dtos.CoordinateDto;
import com.comrades.domain.models.Coordinate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoordinateMapper {

    CoordinateMapper INSTANCE = Mappers.getMapper(CoordinateMapper.class);

    CoordinateDto toCoordinateDto(Coordinate coordinate);

    Coordinate toCoordinate(CoordinateDto coordinateDto);

    List<Coordinate> toCoordinateList(List<CoordinateDto> list);

    List<CoordinateDto> toCoordinateDtoList(List<Coordinate> list);
}
