package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Volunteer;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    final Logger logger = LoggerFactory.getLogger(VolunteerRepository.class);
    List<Volunteer> findByChatId(long chatId);
}
