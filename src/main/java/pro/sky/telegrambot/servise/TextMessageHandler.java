package pro.sky.telegrambot.servise;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TextMessageHandler {

    @Autowired
    private VolunteerRegistrationServise volunteerRegistrationServise;

    @Autowired
    private ApplicationContext applicationContext;


}