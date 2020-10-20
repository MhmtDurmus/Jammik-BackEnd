package be.multimedi.jammik.entities;


import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

// Gemaakt door: Michael Creelle

@Entity
public class Dag {

    @Id
    @NotNull
    @Column(name = "naam")
    private String naam;

    @NotNull
    @Column(name = "OpeningsUur")
    private LocalTime openingsUur;

    @NotNull
    @Column(name = "SluitingsUur")
    private LocalTime sluitingsUur;

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