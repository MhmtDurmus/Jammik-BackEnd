package be.multimedi.jammik.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalTime;

// Gemaakt door: Michael Creelle

@Entity
@Table(name="Dag")
public class Dag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Min(0)
    private int id;

    @NotNull
    @Column(name = "Naam")
    private String naam;

    @NotNull
    @Column(name = "OpeningsUur")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime openingsUur;

    @NotNull
    @Column(name = "SluitingsUur")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime sluitingsUur;

    public Dag() {
    }

    public Dag(int id ,String naam, LocalTime openingsUur, LocalTime sluitingsUur) {
        setId(id);
        setNaam(naam);
        setOpeningsUur(openingsUur);
        setSluitingsUur(sluitingsUur);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id < 0) throw new IllegalArgumentException("id mag niet kleiner zijn dan 0");
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isBlank()) throw new IllegalArgumentException("Naam mag niet null of leeg zijn");
        this.naam = naam;
    }

    public LocalTime getOpeningsUur() {
        return openingsUur;
    }

    public void setOpeningsUur(LocalTime openingsUur) {
        if (openingsUur == null) throw new IllegalArgumentException("OpeningsUur mag niet null zijn");

        if(sluitingsUur != null && openingsUur.isAfter(getSluitingsUur()))
            throw new IllegalArgumentException("openingsUur mag niet later zijn dan sluitingsUur");

        this.openingsUur = openingsUur;
    }

    public LocalTime getSluitingsUur() {
        return sluitingsUur;
    }

    public void setSluitingsUur(LocalTime sluitingsUur) {
        if (sluitingsUur == null) throw new IllegalArgumentException("SluitingsUur mag niet null zijn");

        if (openingsUur != null && sluitingsUur.isBefore(getOpeningsUur()))
            throw new IllegalArgumentException("sluitingsUur mag niet vroeger zijn dan openingsUur");

        this.sluitingsUur = sluitingsUur;
    }
}
