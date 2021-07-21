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
import java.util.Comparator;
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
//-----------------------------Hotel Search---------------------------------
    public List<ResultsVO>  HotelSearch(String searchLocation){

        //setting Headers
        HttpHeaders header=new HttpHeaders();
        header.set("Content-Type","application/json");
        header.set("Accept","application/json");
        header.set("X-RapidAPI-Key",key);
        header.set("X-RapidAPI-Host",host);
        HttpEntity<String> entity=new HttpEntity<>(header);

        //API endpoint locations/search
        final String url="https://hotels4.p.rapidapi.com/locations/search";
        //Adding query parameters
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

        List<EntitiesVO> hotelListEntities = locationVO.getSuggestions()
                .stream()
                .map(SuggestionVO::getEntities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
        ;

        //System.out.println("hotelListEntities"+hotelListEntities.get(0).getDestinationId());

        //getting destinationId
        long destinationId=hotelListEntities.get(0).getDestinationId();
        System.out.println(destinationId);

        //API endpoint properties/list
        final String searchUrl="https://hotels4.p.rapidapi.com/properties/list";

        UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("destinationId", destinationId);

        //getting response for properties/list
        ResponseEntity<HotelVO> hotelResponse=restTemplate.exchange(
                builder1.toUriString(),
                HttpMethod.GET,
                entity,
                HotelVO.class
        );

        HotelVO hotelBody= hotelResponse.getBody();
        System.out.println("Hotel BOdy"+hotelBody);

        //Search results limiting to 10 results
        List<ResultsVO> searchResults=hotelBody
                .getData()
                .getBody()
                .getSearchResults()
                .getResults()
                .stream()
                .sorted(Comparator.comparing(ResultsVO::getStarRating, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList())
        ;

        searchResults.forEach(e->System.out.println("Hotel Name : "+e.getName()+" Star Rating : "+e.getStarRating()+" "+e.getAddress()));

        //return search results
        return searchResults;

    }

//----------------------View Hotel ----------------------------------

    public void viewHotel(String id){
        //setting Headers
        HttpHeaders header=new HttpHeaders();
        header.set("Content-Type","application/json");
        header.set("Accept","application/json");
        header.set("X-RapidAPI-Key",key);
        header.set("X-RapidAPI-Host",host);
        HttpEntity<String> entity=new HttpEntity<>(header);

        //API endpoint properties/get-details
        final String url="https://hotels4.p.rapidapi.com/properties/get-details";

        //Adding query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("id",id);

        //getting response from API
        ResponseEntity<ViewHotelVO> viewHotelResponse=restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ViewHotelVO.class
        );

        //Response Body
        ViewHotelVO viewHotelVO=viewHotelResponse.getBody();

        //Hotel Overview
        List<ViewHotelOverviewSectionsVO> overviewSectionsList=viewHotelVO
                .getData()
                .getBody()
                .getOverview()
                .getOverviewSections()
        ;

        //property amenities
        List<String> propertyAmenities=overviewSectionsList
                .stream()
                .filter(overview->overview.getType().equals("HOTEL_FEATURE"))
                .map(ViewHotelOverviewSectionsVO::getContent)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
        ;
        System.out.println("----------------property amenities---------------");
        propertyAmenities.forEach(System.out::println);

        //Room amenities
        List<String> roomAmenities=overviewSectionsList
                .stream()
                .filter(overview->overview.getType().equals("FAMILY_FRIENDLY_SECTION"))
                .map(ViewHotelOverviewSectionsVO::getContent)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())

        ;

        System.out.println("---------------Room amenities---------------");
        roomAmenities.forEach(System.out::println);

        //LOCATION_SECTION
        List<String> locationSection=overviewSectionsList
                .stream()
                .filter(overview->overview.getType().equals("LOCATION_SECTION"))
                .map(ViewHotelOverviewSectionsVO::getContent)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())

        ;

        System.out.println("-----------Location Section----------------");
        locationSection.forEach(System.out::println);

        //Price
        double price=Double.parseDouble(viewHotelVO.getData()
                .getBody()
                .getPropertyDescription()
                .getFeaturedPrice()
                .getCurrentPrice()
                .getPlain()
        );
        System.out.println("price"+price);
    }



}
