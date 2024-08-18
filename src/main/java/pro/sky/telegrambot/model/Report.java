package pro.sky.telegrambot.model;

import javax.persistence.*;

@Entity
@Table
public class Report {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReport;
    private String theDiet;
    private String behaviour; // поведение
    private String wellBeing; // Общее самочувствие питомца
    private String habits;
    private String state;



    public Report(long idReport, String theDiet, String behaviour, String wellBeing, String habits) {
        this.idReport = idReport;
        this.theDiet = theDiet;
        this.behaviour = behaviour;
        this.wellBeing = wellBeing;
        this.habits = habits;

    }

    public Report() {

    }


    public long getIdReport() {
        return idReport;
    }

    public void setIdReport(long idReport) {
        this.idReport = idReport;
    }

    public String getTheDiet() {
        return theDiet;
    }

    public void setTheDiet(String theDiet) {
        this.theDiet = theDiet;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getWellBeing() {
        return wellBeing;
    }

    public void setWellBeing(String wellBeing) {
        this.wellBeing = wellBeing;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
