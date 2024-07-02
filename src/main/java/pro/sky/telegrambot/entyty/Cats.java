package pro.sky.telegrambot.entyty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cats")
public class Cats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCat;
    private String nameCat;
    private int age;

    public Cats(long idCat, String nameCat, int age) {
        this.idCat = idCat;
        this.nameCat = nameCat;
        this.age = age;
    }

    public Cats() {

    }

    public long getIdCat() {
        return idCat;
    }

    public void setIdCat(long idCat) {
        this.idCat = idCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
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
        Cats cats = (Cats) o;
        return idCat == cats.idCat && age == cats.age && Objects.equals(nameCat, cats.nameCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCat, nameCat, age);
    }
}
