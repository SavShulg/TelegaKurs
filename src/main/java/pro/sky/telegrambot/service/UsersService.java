package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.repository.UsersRepository;

import java.util.Collection;
@Service
public class UsersService {

    private final Logger logger = LoggerFactory.getLogger(UsersService.class);
    private final UsersRepository usersRepository;

    private TelegramBot telegramBot;

    public UsersService(UsersRepository usersRepository, TelegramBot telegramBot) {
        this.usersRepository = usersRepository;
        this.telegramBot = telegramBot;
    }

    /**
     * Метод, который добавляет владельца в базу
     *
     * @param user владелец, которого необходимо добавить в базу
     * @return добавленный владелец
     */
    public Users addUser(Users user) {
        return usersRepository.save(user);
    }

    /**
     * Метод, который вносит изменения в карточку владельца в базе
     *
     * @param user владелец, в карточку которого вносятся изменения
     * @return владелец, в карточку которого были внесены изменения
     */
    public Users editUser(Users user) {
        return usersRepository.save(user);
    }

    /**
     * Метод, который удаляет владельца из базы
     *
     * @param id идентификатор живвладельца
     */
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }

    /**
     * Метод, который выводит всех владельцев из базы
     *
     * @return все владельцы из базы
     */
    public Collection<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    /**
     * Метод, который ищет владельца в базе приюта по ID
     *
     * @return владельца с искомым id
     */
    public Users findUsersById(long id) {
        return usersRepository.findById(id);
    }

    /**
     * Метод, который шлет замечание человеку, который неправильно заполняет отчет
     *
     * @param id владельца
     */
    public void sendM(Long id) {
        Long chatId = usersRepository.findById(id).get().getChatId();
        telegramBot.execute(new SendMessage(chatId, "Ваши документы были заполнены не полностью или не верно, " +
                "пожалуйста проверьте еще раз!"));
    }

}
