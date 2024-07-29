package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;

import java.util.Collection;

@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

/**
 * Метод, который добавляет владельца в базу
 */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Метод, который вносит изменения в карточку владельца в базе
     */

    public User editUser(User user) {
        return userRepository.save(user);
    }
    /**
     * Метод, который удаляет владельца из базы
     */
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    /**
     * Метод, который выводит всех владельцев из базы
     */

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Метод, который ищет владельца в базе приюта по ID
     */

    public User findUserById(long id) {
        return userRepository.findById(id);
    }
}
