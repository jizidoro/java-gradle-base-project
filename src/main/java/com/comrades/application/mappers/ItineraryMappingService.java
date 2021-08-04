/*
 * Copyright 2020 Evgeniy Khyst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comrades.application.mappers;

import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.domain.models.Itinerary;
import com.comrades.persistence.repositories.ICoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service
public class ItineraryMappingService {

    @Autowired
    private ItineraryMapper itineraryMapper;

    @Autowired
    private ICoordinateRepository coordinateRepository;


    public Mono<ItineraryDto> toItineraryDto(Itinerary itinerary) {
        ItineraryDto itineraryDto = itineraryMapper.toItineraryDto(itinerary);
        return Mono.zip(
                coordinateRepository.findByItinerary(itinerary.getId()).collectList().map(itineraryMapper::toCoordinateDtos),
                Mono.just(""),
                (coordinateDtos, unused) -> {
                    itineraryDto.setCoordinates(coordinateDtos);
                    return itineraryDto;
                });
    }
}
