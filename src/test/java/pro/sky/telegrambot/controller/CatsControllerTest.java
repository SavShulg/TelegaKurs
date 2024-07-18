package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pro.sky.telegrambot.model.Cats;
import pro.sky.telegrambot.model.Dogs;
import pro.sky.telegrambot.service.CatService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CatsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    void contextLoads(){}

    @MockBean
    private CatService catService;

    @Test
    void addCats() throws Exception {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);
        Mockito.when(catService.addCats(Mockito.any())).thenReturn(cat);

        mockMvc.perform(post("/cats")
                        .content(objectMapper.writeValueAsString(cat))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cat)));
    }

    @Test
    void editCats() throws Exception {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);

        Cats cat2 = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Chupik");
        cat.setAge(3);

        Mockito.when(catService.editCats(Mockito.any())).thenReturn(cat);

        mockMvc.perform(put("cats")
                        .content(objectMapper.writeValueAsString(cat2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCat").value(1))
                .andExpect(jsonPath("$.nameCat").value("Chupik"));

    }

    @Test
    void deleteCats() throws Exception {
        Dogs dogs = new Dogs();
        dogs.setIdDog(1);
        dogs.setNameDog("Sharik");
        dogs.setAge(2);

        Mockito.doNothing().when(catService).deleteCats(dogs.getIdDog());

        mockMvc.perform(delete("/cats/{id}", 1)
                        .content(objectMapper.writeValueAsString(dogs))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(catService, times(1)).deleteCats(dogs.getIdDog());
    }

    @Test
    void findCats() throws Exception {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);

        List<Cats> animals = new ArrayList<>();
        animals.add(cat);

        Mockito.when(catService.findCatsById(Mockito.anyLong())).thenReturn(cat);
        Mockito.when(catService.findCatsByName(Mockito.anyString())).thenReturn(cat);
        Mockito.when(catService.getAllCats()).thenReturn(animals);

        mockMvc.perform(get("/cats?id={id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cat)));

        mockMvc.perform(get("/cats?name=bulka")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animals)));
    }
}