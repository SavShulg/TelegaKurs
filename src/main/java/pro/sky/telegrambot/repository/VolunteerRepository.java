package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Volunteer;

import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    /**
     * Ищет волонтера по идентификатору чата.
     * chatId идентификатор чата
     * Optional, содержащий волонтера
     */

    Optional<Volunteer> findByChatId(Long chatId);

    Optional<Volunteer> findFirstByIsBusy(boolean bool);
}
