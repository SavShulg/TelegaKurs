package pro.sky.telegrambot.servise;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j

public class VolunteerRegistrationServise {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private Map<Long, Boolean> RegistrationName = new HashMap<>();


    private void registerName(long chatId) {
        TelegramBotServise bot = applicationContext.getBean(TelegramBotServise.class);
        bot.sendMessage(chatId, "Пожалуйста, введите ваше имя:");
        RegistrationName.put(chatId, true);
        }

}
