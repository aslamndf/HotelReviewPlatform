package com.aslam.userService.impl;

import com.aslam.userService.external.services.HotelService;
import com.aslam.userService.model.Hotel;
import com.aslam.userService.model.Rating;
import com.aslam.userService.model.User;
import com.aslam.userService.repo.UserRepository;
import com.aslam.userService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        // UUID is generated automatically by Hibernate
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public User getUserById(UUID userId) {
        //get user from database with the help of user repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        //fetch ratings of the above user from RATING-SERVICE
        //http://localhost:8083/ratings/user/8132a3f4-599e-487d-858a-c5face63f3aa

        Rating[] ratingsOfUsers = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUsers);

        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to HOTEL-SERVICE to get hotel
            //http://localhost:8082/hotels/acba6118-77d6-4339-9f0f-3fe71bc2d9cb
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(UUID.fromString((rating.getHotelId())));
            //logger.info(" response status code: {}", forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);


        return user;

    }

    // ✅ Fallback method MUST be in same class
//    public User ratingHotelFallback(UUID id, Throwable ex) {
//        logger.error("Fallback triggered for userId {}", id, ex);
//
//        return User.builder()
//                .userId(id)
//                .name("Dummy")
//                .email("dummy@gmail.com")
//                .about("Fallback user because dependent service is down")
//                .build();
//    }


    @Override
    public User updateUser(User user, UUID userId) {
        User existingUser = getUserById(userId);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAbout(user.getAbout());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
