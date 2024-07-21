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
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.service.UsersService;

import java.util.LinkedList;
import java.util.List;


import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UsersControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private UsersService usersService;

    @Test
    void addUser() throws Exception {
        Users user = new Users();
        user.setIdUser(1);
        user.setFirstName("Иван");
        user.setLastName("Петров");
        user.setNumberUser("89371234567");
        user.setChatId(454643L);

        Mockito.when(usersService.addUser(Mockito.any())).thenReturn(user);

        mockMvc.perform(
                        post("/users")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }


    @Test
    void editUser() throws Exception {
        Users user = new Users();
        user.setIdUser(1);
        user.setFirstName("Иван");
        user.setLastName("Петров");
        user.setNumberUser("89371234567");
        user.setChatId(454643L);

        Users newUser = new Users();
        user.setIdUser(1);
        user.setFirstName("Михаил");
        user.setLastName("Петров");
        user.setNumberUser("89371234567");
        user.setChatId(454643L);

        Mockito.when(usersService.editUser(Mockito.any())).thenReturn(user);

        mockMvc.perform(
                        put("/users")
                                .content(objectMapper.writeValueAsString(newUser))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(1))
                .andExpect(jsonPath("$.firstName").value("Михаил"));
    }

    @Test
    void deleteUser() throws Exception {
        Users user = new Users();
        user.setIdUser(1);
        user.setFirstName("Иван");
        user.setLastName("Петров");
        user.setNumberUser("89371234567");
        user.setChatId(454643L);

        Mockito.doNothing().when(usersService).deleteUser(user.getIdUser());

        mockMvc.perform(
                        delete("/users/{id}",1)
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());
        Mockito.verify(usersService, times(1)).deleteUser(user.getIdUser());
    }

    @Test
    void findUser() throws Exception {
        Users user = new Users();
        user.setIdUser(1);
        user.setFirstName("Иван");
        user.setLastName("Петров");
        user.setNumberUser("89371234567");
        user.setChatId(454643L);

        List<Users> users = new LinkedList<>();
        users.add(user);

        Mockito.when(usersService.findUsersById(Mockito.anyLong())).thenReturn(user);
        Mockito.when(usersService.getAllUsers()).thenReturn(users);

        mockMvc.perform(
                        get("/users?id=1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));

        mockMvc.perform(
                        get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }
}