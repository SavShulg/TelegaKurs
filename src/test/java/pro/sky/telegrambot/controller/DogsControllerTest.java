package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.pro.packaged.D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambot.model.Dogs;
import pro.sky.telegrambot.service.DogService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class DogsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads(){

    }

    @MockBean
    private DogService dogService;
    @Test
    void addDogs() throws Exception{
        Dogs dogs = new Dogs();
        dogs.setIdDog(1);
        dogs.setNameDog("Sharik");
        dogs.setAge(2);

        Mockito.when(dogService.addDogs(Mockito.any())).thenReturn(dogs);

        mockMvc.perform(post("/dogs")
                        .content(objectMapper.writeValueAsString(dogs))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dogs)));
    }

    @Test
    void editDogs() throws Exception {
        Dogs dogs = new Dogs();
        dogs.setIdDog(1);
        dogs.setNameDog("Sharik");
        dogs.setAge(2);

        Dogs dogs2 = new Dogs();
        dogs.setIdDog(1);
        dogs.setNameDog("Bobik");
        dogs.setAge(3);

        Mockito.when(dogService.editDogs(Mockito.any())).thenReturn(dogs);

        mockMvc.perform(put("/dogs")
                .content(objectMapper.writeValueAsString(dogs2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDog").value(1))
                .andExpect(jsonPath("$.nameDog").value("Sharik"));
    }

    @Test
    void deleteDogs() throws Exception {
        Dogs dogs = new Dogs();
        dogs.setIdDog(1);
        dogs.setNameDog("Sharik");
        dogs.setAge(2);

        Mockito.doNothing().when(dogService).deleteDogs(dogs.getIdDog());

        mockMvc.perform(delete("/dogs/{id}", 1)
                        .content(objectMapper.writeValueAsString(dogs))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(dogService, times(1)).deleteDogs(dogs.getIdDog());
    }

    @Test
    void findDogs() throws Exception{
        Dogs dogs = new Dogs();
        dogs.setIdDog(1);
        dogs.setNameDog("Sharik");
        dogs.setAge(2);

        List<Dogs> animals = new ArrayList<>();
        animals.add(dogs);

        Mockito.when(dogService.findDogsById(Mockito.anyLong())).thenReturn(dogs);
        Mockito.when(dogService.findDogsByName(Mockito.anyString())).thenReturn(dogs);
        Mockito.when(dogService.getAllDogs()).thenReturn(animals);

        mockMvc.perform(get("/dogs?id={id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dogs)));

        mockMvc.perform(get("/dogs?name=Sharik")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dogs)));

        mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animals)));
    }
}