package pro.sky.telegrambot.notification;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.CatsRepository;

import java.util.logging.Logger;

@Service
public class CatScheduler {

    private static final Logger log = Logger.getAnonymousLogger();
    public final TelegramBot telegramBot;

    private final CatsRepository catsRepository;

    public CatScheduler(TelegramBot telegramBot, CatsRepository catsRepository) {
        this.telegramBot = telegramBot;
        this.catsRepository = catsRepository;
    }
}
