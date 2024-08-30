package pro.sky.telegrambot.scheduller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.Cats;
import pro.sky.telegrambot.model.Dogs;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.CatsRepository;
import pro.sky.telegrambot.repository.DogsRepository;
import pro.sky.telegrambot.repository.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
public class NotificationScheduler {

    private final CatsRepository catsRepository;
    private final DogsRepository dogsRepository;
    private final UserRepository userRepository;
    private final TelegramBot telegramBot;

    Logger logger = LoggerFactory.getLogger(NotificationScheduler.class);
    private static final int daysToSend = 30; // срок отправления отчетов (дни)
    private static final int daysExtension = 45; // срок продления (дни)

    public NotificationScheduler(CatsRepository catsRepository, DogsRepository dogsRepository,
                                 UserRepository userRepository, TelegramBot telegramBot) {

        this.catsRepository = catsRepository;
        this.dogsRepository = dogsRepository;
        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0 11 * * *") // Аннотация запускает метод каждый день в 11:00

    public void sendDailyMessage() {
        String message = "Добрый день! Ждем ваш ежедневный отчет.";
        String message2 = "Вам продлили испытательный срок на 15 дней. Ждем ваш ежедневный отчет.";
        List<Cats> catsList = catsRepository.findAll();
        List<Dogs> dogsList = dogsRepository.findAll();
        for (Cats cats : catsList) {
            if (cats.getUser() != null) {
                LocalDate date1 = cats.getDateAdoption();
                LocalDate today = LocalDate.now();
                long differenceInDays = ChronoUnit.DAYS.between(date1, today);
                Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(cats.getUser().getIdUser()));
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (differenceInDays <= daysToSend) {
                        telegramBot.execute(new SendMessage(user.getChatId(), message));
                    } else if (differenceInDays == daysToSend + 1) {
                        telegramBot.execute(new SendMessage(user.getChatId(), "Ваш испытательный срок успешно пройден!"));
                    } else if (differenceInDays != daysToSend + 1) {
                        telegramBot.execute(new SendMessage(user.getChatId(), message2));
                    } else if (differenceInDays == daysToSend && differenceInDays <= daysExtension) {
                        telegramBot.execute(new SendMessage(user.getChatId(), message));
                    } else if (differenceInDays == daysExtension + 1) {
                        telegramBot.execute(new SendMessage(user.getChatId(), "Время испытательного срока вышло. Ожидайте решения приюта."));
                    }
                }
            }
        }
        for (Dogs dogs : dogsList) {
            LocalDate date1 = dogs.getDateAdoption();
            LocalDate today = LocalDate.now();
            long differenceInDays = ChronoUnit.DAYS.between(date1, today);
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(dogs.getUser().getIdUser()));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (differenceInDays <= daysToSend) {
                    telegramBot.execute(new SendMessage(user.getChatId(), message));
                } else if (differenceInDays == daysToSend + 1) {
                    telegramBot.execute(new SendMessage(user.getChatId(), "Ваш испытательный срок успешно пройден!"));
                } else if (differenceInDays != daysToSend + 1) {
                    telegramBot.execute(new SendMessage(user.getChatId(), message2));
                } else if (differenceInDays == daysToSend && differenceInDays <= daysExtension) {
                    telegramBot.execute(new SendMessage(user.getChatId(), message));
                } else if (differenceInDays == daysExtension + 1) {
                    telegramBot.execute(new SendMessage(user.getChatId(), "Время испытательного срока вышло. Ожидайте решения приюта."));
                }
            }
        }
    }
}
