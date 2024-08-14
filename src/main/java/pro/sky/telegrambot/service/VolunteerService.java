package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Метод добавляет волонтера в базу данных.
     */
    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

/**
 * Метод ищет волонтера по уникальному идентификатору Телеграм в базе данных.
 */
    public List<Volunteer> findVolunteerByChatId(Long chatId) {
        return volunteerRepository.findByChatId(chatId);
    }

    /**
     * Метод редактирует волонтера в базе данных.
     */
    public Volunteer editVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    /**
     * Метод удаляет волонтера по уникальному идентификатору из базы данных.
     */
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }

    /**
     * Метод выводит список всех зарегистрированных волонтёров.
     */
    public Collection<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }
}
