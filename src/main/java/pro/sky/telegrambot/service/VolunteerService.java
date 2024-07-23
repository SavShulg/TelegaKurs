package pro.sky.telegrambot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.Collection;
import java.util.Optional;

@Service

public class VolunteerService {


    @Autowired
    private static VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        VolunteerService.volunteerRepository = volunteerRepository;
    }


    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer editVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    //выводит всех волонтеров из коллекции
    public static Collection<Volunteer> getAllVolunteer() {
        return volunteerRepository.findAll();
    }


    public boolean delete(long id) {
        return volunteerRepository.findById(id)
                .map(entity -> {
                    volunteerRepository.delete(entity);
                    return true;
                }).orElse(false);
    }


    public static Optional<Volunteer> findVolunteerById(long id) {
        return volunteerRepository.findById(id);
    }

}
