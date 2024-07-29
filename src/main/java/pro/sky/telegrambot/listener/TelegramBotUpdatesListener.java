package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
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
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.CatsRepository;
import pro.sky.telegrambot.repository.DogsRepository;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.service.SaveContactData;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private DogsRepository dogsRepository;
    private static Constants constants;
    private final UserRepository userRepository;


    public TelegramBotUpdatesListener(TelegramBotConfiguration config, TelegramBot telegramBot,
                                      NotificationTaskRepository repository, CatsRepository catsRepository,
                                      DogsRepository dogsRepository, UserRepository userRepository) {
        this.config = config;
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.catsRepository = catsRepository;
        this.dogsRepository = dogsRepository;
        this.userRepository = userRepository;
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
                                new String[]{"/Dog trainer advice", "/Call volunteer"},
                                new String[]{"/Write data"});
                        replyKeyboardMarkup.oneTimeKeyboard(true);
                        replyKeyboardMarkup.resizeKeyboard(true);
                        replyKeyboardMarkup.selective(true);

                        var sendMessage = new SendMessage(chatId, "Добро пожаловать!");
                        sendMessage.replyMarkup(replyKeyboardMarkup);
                        telegramBot.execute(sendMessage);

                    } else if ("/Info".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, Constants.SAFETYRULESONTHETERRITORY));
                        telegramBot.execute(new SendMessage(chatId, Constants.WORKSCHEDULE));
                        telegramBot.execute(new SendMessage(chatId, Constants.TEXTFOROMISSIONS));
                    } else if ("/Adopt an animal".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, Constants.RULESFORINTRODUCINGPETS));
                        telegramBot.execute(new SendMessage(chatId, Constants.REASONSFORREFUSAL));
                        telegramBot.execute(new SendMessage(chatId, Constants.DOCUMENTSFORADOPT));
                        telegramBot.execute(new SendMessage(chatId, Constants.RECOMMENDATIONSFORTRANSPORTINGPETS));
                        telegramBot.execute(new SendMessage(chatId, Constants.ARRANGEFORPUPPY));
                        telegramBot.execute(new SendMessage(chatId, Constants.ARRANGEFORADULTANIMAL));
                        telegramBot.execute(new SendMessage(chatId, Constants.ARRANGEFORSPECIALANIMAL));
                    } else if ("/Write data".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "Введите через пробел ваше имя, фамилию и телефон: "));

                    } else if ("/Call volunteer".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, Constants.CALL_VOLUNTEER));
                    } else if ("/Dog trainer advice".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, Constants.ADVICEDOGHADLER));
                        telegramBot.execute(new SendMessage(chatId, Constants.RECOMMENDEDDOGHANDLER));
                    } else if ("/Dogs".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "Собака - лучший друг человека!"));
                    } else if ("/Cats".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "Кошки милые, уважаем ваш выбор!"));

                    } else if (text.contains("+")) {
                        String[] result = text.split(" ");
                        User user = new User();
                        user.setFirstName(result[0]);
                        user.setLastName(result[1]);
                        user.setNumberUser(result[2]);
                        userRepository.save(user);
                        telegramBot.execute(new SendMessage(chatId, "Спасибо, данные сохранены."));

                    }

                } else {
                    telegramBot.execute(new SendMessage(chatId, "Извините, такая команда не поддерживается :("));
                }

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


}







