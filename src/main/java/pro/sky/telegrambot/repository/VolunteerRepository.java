package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entyty.Volunteer;



@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findByNameVolunteer(String firstName);


}
