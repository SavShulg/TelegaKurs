package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
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
import pro.sky.telegrambot.constants.Constants;
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


    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBotConfiguration config;
    private final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;
    private CatsRepository catsRepository;
    private static Constants constants;

    public TelegramBotUpdatesListener(TelegramBotConfiguration config, TelegramBot telegramBot,
                                      NotificationTaskRepository repository, CatsRepository catsRepository,
                                      Constants constants) {
        this.config = config;
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.catsRepository = catsRepository;
        this.constants = constants;
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
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                                new String[]{"/Adopt an animal", "/Info"},
                                new String[]{"/Dogs", "/Cats"},
                                new String[]{"/Dog trainer advice"});
                        replyKeyboardMarkup.oneTimeKeyboard(true);
                        replyKeyboardMarkup.resizeKeyboard(true);
                        replyKeyboardMarkup.selective(true);

                        var sendMessage = new SendMessage(chatId, "Добро пожаловать!");
                        sendMessage.replyMarkup(replyKeyboardMarkup);
                        telegramBot.execute(sendMessage);
                    }
                    else if ("/Info".equals(text)){
                        telegramBot.execute(new SendMessage(chatId, Constants.SAFETYRULESONTHETERRITORY));
                        telegramBot.execute(new SendMessage(chatId, Constants.WORKSCHEDULE));
                        telegramBot.execute(new SendMessage(chatId, Constants.TEXTFOROMISSIONS));
                    }else if ("/Adopt an animal".equals(text)){
                        telegramBot.execute(new SendMessage(chatId, Constants.RULESFORINTRODUCINGPETS));
                        telegramBot.execute(new SendMessage(chatId, Constants.REASONSFORREFUSAL));
                        telegramBot.execute(new SendMessage(chatId, Constants.DOCUMENTSFORADOPT));
                        telegramBot.execute(new SendMessage(chatId, Constants.RECOMMENDATIONSFORTRANSPORTINGPETS));
                        telegramBot.execute(new SendMessage(chatId, Constants.ARRANGEFORPUPPY));
                        telegramBot.execute(new SendMessage(chatId, Constants.ARRANGEFORADULTANIMAL));
                        telegramBot.execute(new SendMessage(chatId, Constants.ARRANGEFORSPECIALANIMAL));
                    }else if ("/Dog trainer advice".equals(text)){
                        telegramBot.execute(new SendMessage(chatId, Constants.ADVICEDOGHADLER));
                        telegramBot.execute(new SendMessage(chatId, Constants.RECOMMENDEDDOGHANDLER));
                    }else if ("/Dogs".equals(text)) {
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