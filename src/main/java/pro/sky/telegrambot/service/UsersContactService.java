package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public interface UsersContactService {

    final Logger logger = LoggerFactory.getLogger(UsersContactService.class);

    void addUserContact(Long chatId, String name, int phoneNumber);


}