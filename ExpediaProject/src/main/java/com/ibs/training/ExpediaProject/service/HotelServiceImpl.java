package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.*;
import com.ibs.training.ExpediaProject.dto.HotelDTO;
import com.ibs.training.ExpediaProject.entity.HotelBookingEntity;
import com.ibs.training.ExpediaProject.repository.HotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Value("${RapidAPI.Hotels.Key}")
    private String key;

    @Value("${RapidAPI.host}")
    private String host;

    //API endpoint locations/search
    @Value("${location.search}")
    private String locationSearchUrl;

    //API endpoint properties/list
    @Value("${properties.list}")
    private String listPropertiesUrl;

    //API endpoint properties/get-details
    @Value("${properties.get-details}")
    private String getDetailsUrl;

    private HotelDTO hotelDTO;

    private HotelBookingEntity hotelBookingEntity;

    private final RestTemplate restTemplate;

    private UserServiceImpl userServiceImpl;

    private HotelBookingRepository hotelBookingRepository;

    @Autowired
    public HotelServiceImpl(RestTemplate restTemplate, HotelDTO hotelDTO, UserServiceImpl userServiceImpl,
                            HotelBookingEntity hotelBookingEntity, HotelBookingRepository hotelBookingRepository){
        this.restTemplate=restTemplate;
        this.hotelDTO=hotelDTO;
        this.userServiceImpl=userServiceImpl;
        this.hotelBookingEntity=hotelBookingEntity;
        this.hotelBookingRepository=hotelBookingRepository;
    }

//----------------------------------Hotel Search----------------------------------------------
    @Override
    public List<ResultsVO> HotelSearch(String searchLocation,
                                       String checkin,
                                       String checkout,
                                       String travellers,
                                       String rooms) {

        //setting Headers

        HttpEntity<String> entity = new HttpEntity<>(setHeader());

        //Adding query parameters to location/search url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(locationSearchUrl)
                .queryParam("query", searchLocation);

        //getting response from API
        ResponseEntity<LocationVO> locationResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                LocationVO.class
        );

        if (locationResponse.getStatusCode().is2xxSuccessful() &&
                Integer.parseInt(locationResponse.getBody().getMoresuggestions())!=0) {

            //Converting checkin and checkout to Date type and travellers to int and adding to HotelDTO class

            LocalDate checkinDate=LocalDate.parse(checkin);
            LocalDate checkoutDate=LocalDate.parse(checkout);
            int travellersNumber=Integer.parseInt(travellers);

            hotelDTO.setCheckin(checkinDate);
            hotelDTO.setCheckout(checkoutDate);
            hotelDTO.setTravellers(travellersNumber);
            hotelDTO.setRooms(Integer.parseInt(rooms));

            //response body
            LocationVO locationVO = locationResponse.getBody();

            List<EntitiesVO> hotelListEntities = locationVO.getSuggestions()
                    .stream()
                    .map(SuggestionVO::getEntities)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList())
            ;

            //adding query parameters to properties/list
            UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(listPropertiesUrl)
                    .queryParam("destinationId",hotelListEntities.get(0).getDestinationId());

            //getting response for properties/list
            ResponseEntity<HotelVO> hotelResponse = restTemplate.exchange(
                    builder1.toUriString(),
                    HttpMethod.GET,
                    entity,
                    HotelVO.class
            );

            HotelVO hotelBody = hotelResponse.getBody();
            System.out.println("Hotel BOdy" + hotelBody);

            //Search results limiting to 10 results
            List<ResultsVO> searchResults = hotelBody
                    .getData()
                    .getBody()
                    .getSearchResults()
                    .getResults()
                    .stream()
                    .sorted(Comparator.comparing(ResultsVO::getStarRating, Comparator.reverseOrder()))
                    .limit(10)
                    .collect(Collectors.toList())
            ;
            searchResults.forEach(e -> System.out.println("Hotel Name : " + e.getName() + " Star Rating : " + e.getStarRating() + " " + e.getAddress()));

            //return search results
            return searchResults;

        }
        else {
            return null;
        }
    }
//--------------------------------------View Hotel ------------------------------------------
    @Override
    public HotelDTO viewHotel(String id, String hotelName, String starRating, String address){
        //setting Headers
        HttpEntity<String> entity=new HttpEntity<>(setHeader());

        /* convert starRating to integer and adding all to HotelDTO*/
        double rating=Double.parseDouble(starRating);
        hotelDTO.setHotelId(id);
        hotelDTO.setHotelName(hotelName);
        hotelDTO.setStarRating(rating);
        hotelDTO.setAddress(address);

        //Adding query parameters to properties/get-details
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getDetailsUrl)
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

        //adding property amenities to HotelDTO
        hotelDTO.setPropertyAmenities(propertyAmenities);

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
        //adding room amenities to HotelDTO
        hotelDTO.setRoomAmenities(roomAmenities);

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
        //adding loction section to HotelDTO
        hotelDTO.setLocationSection(locationSection);

        locationSection.forEach(System.out::println);

        //Price
        double price=Double.parseDouble(viewHotelVO.getData()
                .getBody()
                .getPropertyDescription()
                .getFeaturedPrice()
                .getCurrentPrice()
                .getPlain()
        );
        //adding price to HotelDTO

        hotelDTO.setPrice(price);

        hotelDTO.setTotalPrice(getHotelPrice());

        return hotelDTO;
    }


//---------------------------booking--------------------------------------------

    @Override
    public HotelDTO hotelBooking() {
        //get User
        String user= userServiceImpl.getUser();
        if(user.equals("anonymousUser")){
            user="guest";
        }

        Period period=Period.between(hotelDTO.getCheckin(),hotelDTO.getCheckout());
        int stayDuration= period.getDays()==0?1: period.getDays();

        //generate booking id
        int random=new Random(1000).nextInt();
        String bookingId="bookingId".concat(String.valueOf(random)).concat(hotelDTO.getHotelId());

        //Adding booking details to HotelBookingEntity
        hotelBookingEntity.setUsername(user);
        hotelBookingEntity.setCheckin(hotelDTO.getCheckin());
        hotelBookingEntity.setCheckout(hotelDTO.getCheckout());
        hotelBookingEntity.setHotelId(hotelDTO.getHotelId());
        hotelBookingEntity.setTravellers(hotelDTO.getTravellers());
        hotelBookingEntity.setRoomrent(hotelDTO.getTotalPrice());
        hotelBookingEntity.setDuration(stayDuration);
        hotelBookingEntity.setBookingId(bookingId);

        //saving to repository
        hotelBookingRepository.save(hotelBookingEntity);

        return hotelDTO;
    }

    //method to get hotel images
    @Override
    public List<String> getHotelImages(){
        List<String> imageList=new ArrayList<>();
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel1.jpg");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel2.jpg");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel3.jfif");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel4.webp");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel5.jpg");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel6.jpg");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel7.webp");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel8.jpg");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel9.webp");
        imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel10.jpg");
        return imageList;
    }

    @Override
    public HotelBookingEntity getItinerary(String bookingId) {
        return hotelBookingRepository.findById(bookingId).orElse(null);
    }

    public double getHotelPrice(){
        Period period=Period.between(hotelDTO.getCheckin(),hotelDTO.getCheckout());
        int stayDuration= period.getDays()==0?1: period.getDays();
        return  hotelDTO.getRooms()* hotelDTO.getPrice()*stayDuration;
    }

    public HttpHeaders setHeader(){
        HttpHeaders header=new HttpHeaders();
        header.set("Content-Type","application/json");
        header.set("Accept","application/json");
        header.set("X-RapidAPI-Key",key);
        header.set("X-RapidAPI-Host",host);
        return header;
    }


}
