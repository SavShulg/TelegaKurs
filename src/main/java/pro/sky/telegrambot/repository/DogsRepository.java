package pro.sky.telegrambot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Dogs;

public interface DogsRepository extends JpaRepository<Dogs, Long> {
    final Logger logger = LoggerFactory.getLogger(DogsRepository.class);
    Dogs findByNameDog(String name);
}
