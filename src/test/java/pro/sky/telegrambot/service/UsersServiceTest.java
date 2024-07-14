package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    private final UsersRepository usersRepository = mock(UsersRepository.class);
    private final TelegramBot telegramBot = mock(TelegramBot.class);
    private UsersService out;

    @BeforeEach
    public void initOut() {
        out = new UsersService(usersRepository, telegramBot);
    }

    @Test
    void addUser() {
        Users user = new Users();
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(user, out.addUser(user));
    }

    @Test
    void editUser() {
        Users user = new Users();
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(user, out.editUser(user));
    }

    @Test
    void getAllUsers() {
        Users user1 = new Users();
        Users user2 = new Users();
        Users user3 = new Users();
        List<Users> exp = new ArrayList<>();
        exp.add(user1);
        exp.add(user2);
        exp.add(user3);
        when(usersRepository.findAll()).thenReturn(exp);
        assertEquals(exp,out.getAllUsers());
    }
    @Test
    void findUsersById() {
        Users user1 = new Users();
        Users user2 = new Users();
        Users user3 = new Users();
        when(usersRepository.findById(1)).thenReturn(user2);
        assertEquals(user2,out.findUsersById(1));

    }
}