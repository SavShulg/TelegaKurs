package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.AppPhoto;

public interface PhotoRepository extends JpaRepository<AppPhoto, Long> {
    final Logger logger = LoggerFactory.getLogger(PhotoRepository.class);

    @Query("SELECT MAX(ap)  FROM AppPhoto ap WHERE ap.chatId = :chatId GROUP BY ap.chatId ")
    AppPhoto findLastReport1(long chatId);
}
