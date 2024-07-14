package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Cats;

import java.util.List;

public interface CatsRepository extends JpaRepository<Cats, Long> {
    final Logger logger = LoggerFactory.getLogger(CatsRepository.class);

    Cats findCatsByNameCat(String name);

}
