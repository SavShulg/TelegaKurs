package pro.sky.telegrambot.notification;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.util.logging.Logger;

@Service
public class NotificationScheduler {

    private static final Logger log = Logger.getAnonymousLogger();
    public final TelegramBot telegramBot;
    private final NotificationTaskRepository repository;


    public NotificationScheduler(TelegramBot telegramBot, NotificationTaskRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }
}
