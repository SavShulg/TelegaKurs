package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    User findById(long id);

    User findByNumberUser(String numberUser);

    User findByChatId(Long chatId);

}
