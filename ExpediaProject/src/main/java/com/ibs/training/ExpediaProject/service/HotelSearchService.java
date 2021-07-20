package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        //System.out.println(locationResponse);
        LocationVO location =locationResponse.getBody();
        System.out.println(location);
        List<SuggestionVO> hotelList= location.getSuggestions();

        List<EntitiesVO> hotelListEntities= location.getSuggestions()
                .stream()
                .map(suggestion -> suggestion.getEntities())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("hotelListEntities"+hotelListEntities.get(0).getDestinationId());

        long destinationId=hotelListEntities.get(0).getDestinationId();
        System.out.println(destinationId);

        final String searchUrl="https://hotels4.p.rapidapi.com/properties/list";
        UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("destinationId", destinationId);


        //properties/list
        ResponseEntity<HotelVO> hotelResponse=restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                HotelVO.class
        );

        HotelVO hotelBody= hotelResponse.getBody();
        System.out.println(hotelBody);

        List<ResultsVO> searchResults=hotelBody.getData().getBody().getSearchResults().getResults();
        searchResults.stream().forEach(e->System.out.println("Hotel Name : "+e.getName()+" Star Rating : "+e.getStarRating()+" "+e.getAddress()));

        //return location;

    }



}
