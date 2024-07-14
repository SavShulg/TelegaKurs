package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
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
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Home
 * class listenera
 */

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, NotificationTaskRepository notificationTaskRepository) {
        this.telegramBot = telegramBot;
        this.repository = notificationTaskRepository;
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

                    } else if ("/volonteer".equals(text)) {
                        telegramBot.execute(new SendMessage(chatId, "К сожалению свободных волонтеров нет"));

                    }

                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
























