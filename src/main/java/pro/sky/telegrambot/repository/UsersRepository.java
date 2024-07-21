package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Users;



@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    final Logger logger = LoggerFactory.getLogger(UsersRepository.class);

    Users findById(long id);

    Users findByNumberUser(String numberUser);

    Users findByChatId(Long chatId);


}