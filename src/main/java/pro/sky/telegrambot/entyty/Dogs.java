package pro.sky.telegrambot.entyty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Dogs")
public class Dogs {
    /**
     * @author Home
     * class listener
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDog;
    private String nameDog;
    private int age;

    public Dogs(long idDog, String nameDog, int age) {
        this.idDog = idDog;
        this.nameDog = nameDog;
        this.age = age;
    }

    public Dogs() {

    }

    public long getIdDog() {
        return idDog;
    }

    public void setIdDog(long idDog) {
        this.idDog = idDog;
    }

    public String getNameDog() {
        return nameDog;
    }

    public void setNameDog(String nameDog) {
        this.nameDog = nameDog;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dogs dogs = (Dogs) o;
        return idDog == dogs.idDog && age == dogs.age && Objects.equals(nameDog, dogs.nameDog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDog, nameDog, age);
    }
}
