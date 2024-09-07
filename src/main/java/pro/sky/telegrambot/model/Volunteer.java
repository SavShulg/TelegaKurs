package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private long chatId;
    private String volunteerName;

    public Volunteer() {
    }

    public Volunteer(long id, long chatId, String volunteerName) {
        this.id = id;
        this.chatId = chatId;
        this.volunteerName = volunteerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return id == volunteer.id && chatId == volunteer.chatId && Objects.equals(volunteerName, volunteer.volunteerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, volunteerName);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", volunteerName='" + volunteerName + '\'' +
                '}';
    }
}
