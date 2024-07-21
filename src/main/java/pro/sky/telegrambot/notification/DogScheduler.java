package pro.sky.telegrambot.notification;

import com.pengrad.telegrambot.TelegramBot;
import pro.sky.telegrambot.repository.DogsRepository;

import java.util.logging.Logger;

public class DogScheduler {

    private static final Logger log = Logger.getAnonymousLogger();
    public final TelegramBot telegramBot;

    private final DogsRepository repository;

    public DogScheduler(TelegramBot telegramBot, DogsRepository repository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
    }
}
