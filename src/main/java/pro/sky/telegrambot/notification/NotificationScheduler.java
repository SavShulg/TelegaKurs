package pro.sky.telegrambot.notification;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
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

    @Scheduled(cron = "0 0/1 * * * *")
    public void checkNotifications() {
        repository.findNotificationTasksByTaskDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(task -> {
                    telegramBot.execute(new SendMessage(task.getChatId(), task.getText()));
                    log.info("Message has been sent!");
                    repository.deleteById(task.getId());
                });

    }
}
