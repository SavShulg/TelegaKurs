package pro.sky.telegrambot.model;
import javax.persistence.*;
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
    private int age;

    public Cats(long idCat, String nameCat, String breed, String food, int age) {
        this.idCat = idCat;
        this.nameCat = nameCat;
        this.breed = breed;
        this.food = food;
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

