package pro.sky.telegrambot.repository;

import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entyty.Dogs;

import java.util.logging.Logger;

public interface DogsRepository extends JpaRepository<Dogs, Long> {
    final Logger logger = (Logger) LoggerFactory.getLogger(DogsRepository.class);

    Dogs findByNameDog(String name);
}
