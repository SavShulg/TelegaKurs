package pro.sky.telegrambot.servise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class VolunteerServise {
    @Autowired
    private VolunteerRepository volunteerRepository;

    public VolunteerServise(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Mетод добавления волонтера в БД.
     */
    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    /**
     * Mетод поиска волонтера в БД по id.
     */
    public Optional<Volunteer> findVolunteerById(long id) {
        return volunteerRepository.findById(id);
    }

    /**
     * Метод ищет свободного волонтера в базе данных.
     */
    public Optional<Volunteer> findFreeVolunteer() {
        return volunteerRepository.findFirstByIsBusy(false);
    }

    /** Метод удаляет волонтера из БД по id.
     *
     * @param id
     */
    public void deleteVolunteer(long id) {
        volunteerRepository.deleteById(id);
    }

    /** Метод выводит всех зарегистрированных волнонтеров.
     *
      * @return
     */
    public Collection<Volunteer> findAllVolunteers() {
        return volunteerRepository.findAll();
    }
}
