package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.LocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class HotelSearchService {

    @Value("${RapidAPI.Key}")
    private String key;

    @Value("${RapidAPI.host}")
    private String host;


    private final RestTemplate restTemplate;

    @Autowired
    public HotelSearchService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public void HotelSearch(String searchLocation){

        //setting Headers
        HttpHeaders header=new HttpHeaders();
        header.set("Content-Type","application/json");
        header.set("Aceept","application/json");
        header.set("X-RapidAPI-Key",key);
        header.set("X-RapidAPI-Host",host);
        HttpEntity<String> entity=new HttpEntity<String>(header);

        //Adding query parameters
        final String url="https://hotels4.p.rapidapi.com/locations/search";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", searchLocation);

        //getting response from API
        ResponseEntity<LocationVO> locationResponse=restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                LocationVO.class
        );

            LocationVO locationVO=locationResponse.getBody();
            System.out.println("location Vo"+locationVO);
    }



}
