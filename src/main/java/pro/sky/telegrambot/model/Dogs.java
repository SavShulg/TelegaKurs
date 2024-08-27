package pro.sky.telegrambot.model;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name = "Dogs")
public class Dogs {
    /*
     * @author Home
     * class listener
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDog;
    private String nameDog;
    private String breed;
    private String food;
    @ManyToOne
    private User user;
    private LocalDate dateAdoption;
    private int age;

    public Dogs(long idDog, String nameDog, String breed, String food, User user, LocalDate dateAdoption, int age) {
        this.idDog = idDog;
        this.nameDog = nameDog;
        this.breed = breed;
        this.food = food;
        this.user = user;
        this.dateAdoption = dateAdoption;
        this.age = age;
    }

    public Dogs() {

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
        Dogs dogs = (Dogs) o;
        return idDog == dogs.idDog && age == dogs.age && Objects.equals(nameDog, dogs.nameDog) && Objects.equals(breed, dogs.breed) && Objects.equals(food, dogs.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDog, nameDog, breed, food, age);
    }

    @Override
    public String
    toString() {
        return "Dogs{" +
                "idDog=" + idDog +
                ", nameDog='" + nameDog + '\'' +
                ", breed='" + breed + '\'' +
                ", food='" + food + '\'' +
                ", age=" + age +
                '}';
    }
}




