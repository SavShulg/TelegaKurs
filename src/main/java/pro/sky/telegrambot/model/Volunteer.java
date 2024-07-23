package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс, представляющий волонтера
 * c занесением в БД.
 */
@Entity
@Table(name = "volunteers", schema = "public")

public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVolunteer;
    private String nameVolunteer;

    public Volunteer(long idVolunteer, String nameVolunteer) {
        this.idVolunteer = idVolunteer;
        this.nameVolunteer = nameVolunteer;
    }
    public Volunteer() {
    }

    public long getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(long idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    public String getNameVolunteer() {
        return nameVolunteer;
    }

    public void setNameVolunteer(String nameVolunteer) {
        this.nameVolunteer = nameVolunteer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return idVolunteer == volunteer.idVolunteer && Objects.equals(nameVolunteer, volunteer.nameVolunteer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVolunteer, nameVolunteer);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "idVolonteer=" + idVolunteer +
                ", nameVolonteer='" + nameVolunteer + '\'' +
                '}';
    }
}