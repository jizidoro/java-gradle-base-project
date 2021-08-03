package com.comrades.application.externals;

import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.itinerary.dtos.CoordinateDto;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class BusLineExternal {

    public BusLineDto[] findAllBusLine() throws URISyntaxException, IOException, InterruptedException {

        var uri = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
        var encoder = uri.replace("%", "%25");

        HttpResponse<String> response = getHttpResponse(encoder);

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        BusLineDto[] busLineDtos = {};
        if (responseStatusCode == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            busLineDtos = objectMapper.readValue(responseBody, BusLineDto[].class);

            return busLineDtos;
        }

        return busLineDtos;
    }


    public ItineraryDto findItineraryByLine(int lineNumber) throws URISyntaxException, IOException, InterruptedException {

        var uri = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + String.valueOf(lineNumber);
        HttpResponse<String> response = getHttpResponse(uri);

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();


        if (responseStatusCode == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            var itineraryDto = new ItineraryDto();

            Map<String, Object> itineraryHashMap = new ObjectMapper().readValue(responseBody, HashMap.class);

            itineraryDto.idlinha = tryParseInt((String) itineraryHashMap.get("idlinha"));
            itineraryDto.codigo = (String) itineraryHashMap.get("codigo");
            itineraryDto.nome = (String) itineraryHashMap.get("nome");

            var oto = new ArrayList<CoordinateDto>();

            for (var item : itineraryHashMap.entrySet()) {
                var isLatitude = tryParseInt(item.getKey()) >= 0;
                if (isLatitude) {
                    var coordinate =
                            objectMapper.convertValue(item.getValue(), CoordinateDto.class);

                    oto.add(coordinate);
                }
            }
            itineraryDto.setCoordinatesDto(oto);
            return itineraryDto;
        }

        return null;
    }

    private HttpResponse<String> getHttpResponse(String encoder) throws IOException, InterruptedException {
        var encodedUrl = URI.create(encoder);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(encodedUrl)
                .headers("Content-Type", "application/json; utf-8")
                .headers("Accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
