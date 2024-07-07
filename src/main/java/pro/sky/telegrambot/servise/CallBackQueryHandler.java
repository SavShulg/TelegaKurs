package pro.sky.telegrambot.servise;

import com.pengrad.telegrambot.model.BotCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CallBackQueryHandler {
    private final VolunteerRegistrationServise volunteerRegistrationServise;
    private final TextMessageHandler textMessageHandler;
    private final ApplicationContext applicationContext;

    @Autowired
    public CallBackQueryHandler(VolunteerRegistrationServise volunteerRegistrationServise, TextMessageHandler textMessageHandler, ApplicationContext applicationContext) {
        this.volunteerRegistrationServise = volunteerRegistrationServise;
        this.textMessageHandler = textMessageHandler;
        this.applicationContext = applicationContext;
    }

    public void handleCallbackQuery(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        TelegramBotServise bot = applicationContext.getBean(TelegramBotServise.class);


    }
}