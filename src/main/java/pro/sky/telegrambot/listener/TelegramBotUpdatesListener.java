package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.TelegramBotConfiguration;
import pro.sky.telegrambot.repository.CatsRepository;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Home
 * class listener
 */

@Service
class TelegramBotUpdatesListener implements UpdatesListener {

    static final String INFO = "Этот приют содержит много собак и кошек разных возрастов и пород. \n" +
            "Вы можете стать счастливым хозяином компаньона! \n" +
            "Наш приют находится в Городе Красноярск по улице Ленина 51/4 \n" +
            "Работаем каждый день с 8:00 до 18:00";

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBotConfiguration config;
    private final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;
    private CatsRepository catsRepository;

    public TelegramBotUpdatesListener(TelegramBotConfiguration config, TelegramBot telegramBot,
                                      NotificationTaskRepository repository) {
        this.config = config;
        this.telegramBot = telegramBot;
        this.repository = repository;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "chatId",
                    description = "Сообщение, которое выведет бот при /start",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Update.class))
                    )
            )
    })




    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            var message = update.message();
            if (message != null) {
                var text = update.message().text();
                var chatId = update.message().chat().id();

                if (text != null) {
                    if ("/start".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "Добро пожаловать!"));
                    }
                    else if ("/info".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, INFO));
                    }
                    else if ("/Dogs".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "Собака - лучший друг человека!"));
                    }
                    else if ("/Cats".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "Кошки милые, уважаем ваш выбор!"));

                    } else telegramBot.execute(new SendMessage(chatId, "Извините, такая команда не поддерживается :("));
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


}