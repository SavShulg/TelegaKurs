package pro.sky.telegrambot.model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Cats")
public class Cats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCat;
    private String nameCat;
    private String breed;
    private String food;
    @ManyToOne
    private User user;
    private LocalDate dateAdoption;
    private int age;

    public Cats(long idCat, String nameCat, String breed, String food, User user, LocalDate dateAdoption, int age) {
        this.idCat = idCat;
        this.nameCat = nameCat;
        this.breed = breed;
        this.food = food;
        this.user = user;
        this.dateAdoption = dateAdoption;
        this.age = age;
    }

    public Cats() {

    }

    public LocalDate getDateAdoption() {
        return dateAdoption;
    }

    public void setDateAdoption(LocalDate dateAdoption) {
        this.dateAdoption = dateAdoption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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
        return idCat == cats.idCat && age == cats.age && Objects.equals(nameCat, cats.nameCat) && Objects.equals(breed, cats.breed) && Objects.equals(food, cats.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCat, nameCat, breed, food, age);
    }

    @Override
    public String toString() {
        return "Cats{" +
                "idCat=" + idCat +
                ", nameCat='" + nameCat + '\'' +
                ", breed='" + breed + '\'' +
                ", food='" + food + '\'' +
                ", age=" + age +
                '}';
    }
}

