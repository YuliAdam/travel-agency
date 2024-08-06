package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.client.ExternalClient;
import org.example.controller.request.HotelRequest;
import org.example.controller.request.UserRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.UserResponse;
import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Role;
import org.example.service.FileStorageService;
import org.example.service.HotelService;
import org.example.service.MoneyService;
import org.example.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class HotelControllerTest {
    @InjectMocks
    HotelController hotelController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    HotelService hotelService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserService userService;

    @Autowired
    MoneyService moneyService;

    @Autowired
    ExternalClient externalClient;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void resetDb() {
        hotelService.deleteAllHotel();
    }

    @BeforeEach
    public void createDB() {
        userService.createUser(new UserRequest(1l, "user1", "user1@", "111111", "1111", Role.ADMIN));
        Hotel hotel1 = new Hotel(1l, Country.BY, "Hotel1", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel2 = new Hotel(2l, Country.BY, "Hotel2", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel3 = new Hotel(3l, Country.BY, "Hotel3", 5, 5, true, true, true, true, true, true, null);
        hotelService.createHotel(new HotelRequest(hotel1));
        hotelService.createHotel(new HotelRequest(hotel2));
        hotelService.createHotel(new HotelRequest(hotel3));
    }

    @Test
    void givenHotels_whenSearchHotels() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel1 = new Hotel(1l, Country.BY, "Hotel1", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel2 = new Hotel(2l, Country.BY, "Hotel2", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel3 = new Hotel(3l, Country.BY, "Hotel3", 5, 5, true, true, true, true, true, true, null);
        List<Hotel> extendedHotel = List.of(hotel1, hotel2, hotel3);
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search").with(user(User.builder()
                        .username(user1.getLogin())
                        .password(user1.getPassword())
                        .roles(user1.getRole().name())
                        .build())))
                .andExpect(view().name("hotels"))
                .andExpect(model().attribute("hotels", extendedHotel.stream().map(HotelResponse::new).toList()))
                .andReturn()
                .getModelAndView();
        System.out.println(relult.getModel());
    }

    @Test
    void givenHotels_whenSearchHotels_andListIsEmpty() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search")
                        .with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build())))
                .andExpect(view().name("hotels"))
                .andExpect(model().attribute("hotels", Collections.emptyList()))
                .andReturn().
                getModelAndView();
        System.out.println(relult.getModel());
    }

    @Test
    void givenHotels_whenSearchHotels_andSortIsName() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel1 = new Hotel(1l, Country.BY, "Hotel1", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel2 = new Hotel(2l, Country.BY, "Hotel2", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel3 = new Hotel(3l, Country.BY, "Hotel3", 5, 5, true, true, true, true, true, true, null);
        Hotel aHotel = new Hotel(4l, Country.BY, "A", 5, 5, true, true, true, true, true, true, null);
        hotelService.createHotel(new HotelRequest(aHotel));
        List<Hotel> extendedHotel = List.of(aHotel, hotel1, hotel2, hotel3);
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search").with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build()))
                        .param("sort", "name"))
                .andExpect(view().name("hotels"))
                .andExpect(model().attribute("hotels", extendedHotel.stream().map(HotelResponse::new).toList()))
                .andReturn()
                .getModelAndView();
        System.out.println(relult.getModel());
    }

    @Test
    void givenHotels_whenSearchHotels_andPageNumberIsOne() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search").with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build()))
                        .param("pageNumber", "1"))
                .andExpect(view().name("hotels"))
                .andExpect(model().attribute("hotels", Collections.emptyList()))
                .andReturn()
                .getModelAndView();
        System.out.println(relult.getModel());
    }

    @Test
    void givenHotels_whenSearchHotels_andPageSizeIsOne() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel1 = new Hotel(1l, Country.BY, "Hotel1", 5, 5, true, true, true, true, true, true, null);
        List<Hotel> extendedHotel = List.of(hotel1);
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search").with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build()))
                        .param("pageSize", "1"))
                .andExpect(view().name("hotels"))
                .andExpect(model().attribute("hotels", extendedHotel.stream().map(HotelResponse::new).toList()))
                .andReturn()
                .getModelAndView();
        System.out.println(relult.getModel());
    }

    @Test
    void givenHotels_whenSearchHotels_andParamtrIsHotel1() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel1 = new Hotel(1l, Country.BY, "Hotel1", 5, 5, true, true, true, true, true, true, null);
        List<Hotel> extendedHotel = List.of(hotel1);
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search").with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build()))
                        .param("paramtr", "Hotel1"))
                .andExpect(view().name("hotels"))
                .andExpect(model().attribute("hotels", extendedHotel.stream().map(HotelResponse::new).toList()))
                .andReturn()
                .getModelAndView();
        System.out.println(relult.getModel());
    }



    @Test
    void whenDeleteHotel_thenDeletingShouldBeSuccessful() throws Exception {
        Hotel hotel4 = new Hotel(4l, Country.BY, "Hotel4", 5, 5, true, true, true, true, true, true, null);
        hotelService.createHotel(new HotelRequest(hotel4));
        Hotel hotel1 = new Hotel(1l, Country.BY, "Hotel1", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel2 = new Hotel(2l, Country.BY, "Hotel2", 5, 5, true, true, true, true, true, true, null);
        Hotel hotel3 = new Hotel(3l, Country.BY, "Hotel3", 5, 5, true, true, true, true, true, true, null);
        List<Hotel> extendedHotel = List.of(hotel1, hotel2, hotel3);
        UserResponse user1 = userService.getUser(1l);
        System.out.println(mockMvc.perform(MockMvcRequestBuilders.delete("/hotel/4/delete").with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build())).with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("hotels", extendedHotel.stream().map(HotelResponse::new).toList()))
                .andReturn()
                .getModelAndView().getModel());
    }

    @Test
    void deleteHotels_ifHotelNotExist() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        mockMvc.perform(MockMvcRequestBuilders.delete("/hotel/4/delete").with(user(User.builder()
                        .username(user1.getLogin())
                        .password(user1.getPassword())
                        .roles(user1.getRole().name())
                        .build())).with(csrf()))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }
    public static String asJsonString(final Object object) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            final ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
            final String jsonContent = mapper.writeValueAsString(object);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenHotels_whenCreateHotel() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel4 = new Hotel(4l, Country.BY, "Hotel4", 5, 5, true, true, true, true, true, true, null);
        System.out.println(this.mockMvc.perform(MockMvcRequestBuilders.post("/hotel/create")
                        .header("hotel", new HotelRequest(hotel4))
                        .content(asJsonString(hotel4))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("hotel", new HotelResponse(hotel4))
                        .secure(true)
                        .with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build()))
                        .with(csrf()))
                .andExpect(status().isCreated()));
    }

    @Test
    public void givenHotels_whenUpdateHotel() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel updatedHotel = new Hotel(1l, Country.BY, "Updated Hotel", 5, 5, true, true, true, true, true, true, null);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/1/redirect/update")
                        .with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build())))
                .andExpect(view().name("hotel"))
                .andExpect(model().attribute("hotel", hotelService.getHotel(1l)))
                .andReturn().
                getModelAndView();
        this.mockMvc.perform(MockMvcRequestBuilders.put("/hotel/update")
                .with(user(User.builder()
                        .username(user1.getLogin())
                        .password(user1.getPassword())
                        .roles(user1.getRole().name())
                        .build()))
                .with(csrf())
                .content(asJsonString(updatedHotel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        ModelAndView result = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/1/redirect/update")
                        .with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build())))
                .andExpect(view().name("hotel"))
                .andExpect(model().attribute("hotel", new HotelResponse(updatedHotel)))
                .andReturn().
                getModelAndView();
        System.out.println(result.getModel());
    }

    @Test
    void givenImage_whenSearchImage() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel4 = new Hotel(4l, Country.BY, "Hotel4", 5, 5, true, true, true, true, true, true, "img1, img2, img3");
        hotelService.createHotel(new HotelRequest(hotel4));
        ModelAndView relult = this.mockMvc.perform(MockMvcRequestBuilders.get("/hotel/4/getImage").with(user(User.builder()
                        .username(user1.getLogin())
                        .password(user1.getPassword())
                        .roles(user1.getRole().name())
                        .build())))
                .andExpect(view().name("images"))
                .andExpect(model().attribute("images", List.of("/img/4/img1", "/img/4/ img2", "/img/4/ img3")))
                .andReturn()
                .getModelAndView();
        System.out.println(relult.getModel());
    }

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        UserResponse user1 = userService.getUser(1l);
        Hotel hotel4 = new Hotel(4l, Country.BY, "Hotel4", 5, 5, true, true, true, true, true, true, null);
        System.out.println(this.mockMvc.perform(MockMvcRequestBuilders.post("/hotel/create")
                        .with(user(User.builder()
                                .username(user1.getLogin())
                                .password(user1.getPassword())
                                .roles(user1.getRole().name())
                                .build()))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new HotelRequest(hotel4)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()));
    }


}