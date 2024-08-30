package pro.sky.telegrambot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final UserRepository usersRepository = mock(UserRepository.class);

    @Test
    public void testAddUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.editUser(user);
        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testEditUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.editUser(user);
        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        long id = 1L;
        doNothing().when(userRepository).deleteById(id);
        userService.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        Collection<User> result = userService.getAllUsers();
        assertNotNull(result);
        Assertions.assertEquals(result.size(), users.size());
        List<User> all = verify(userRepository, times(1)).findAll();
    }
    @Test
    public void testFindUsersById() {
        long id = 1;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(user);
        User result = userService.findUserById(id);
        assertNotNull(result);
        assertEquals(result, user);
        verify(userRepository, times(1)).findById(id);
    }
}