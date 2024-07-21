package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entyty.Volunteer;

import java.util.Optional;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findByNameVolunteer(String firstName);

    Optional<Volunteer> findByChatId(long chatId);
    Optional<Volunteer> findFirstByIsBusy(boolean bool);
}
