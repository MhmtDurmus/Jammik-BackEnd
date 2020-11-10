package be.multimedi.jammik.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

import static be.multimedi.jammik.tools.StringTool.validEmail;


// Gemaakt door: Michael Creelle

@Entity
@Table(name = "Klant")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Klant {

    @Id
    @NotNull
    @Email
    @Unique
    @Column(name = "Email")
    protected String email;

    @NotNull
    @Column(name = "Naam")
    protected String naam;

    @NotNull
    @Column(name = "Voornaam")
    protected String voornaam;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "Wachtwoord")
    protected String wachtwoord;

    @NotNull
    @Column(name = "Krediet")
    protected double krediet;


    @NotNull
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "Klant_Email", referencedColumnName = "Email")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Reservatie> reservaties;

    @NotNull
    @OneToMany(cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "Klant_Email", referencedColumnName = "Email")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<BestellingVerzameling> bestellingVerzamelingen;


    public Klant() {
    }

    public Klant(@Email String email, String naam, String voornaam, String wachtwoord, double krediet, List<Reservatie> reservaties, List<BestellingVerzameling> bestellingVerzamelingen) {
        setEmail(email);
        setNaam(naam);
        setVoornaam(voornaam);
        setWachtwoord(wachtwoord);
        setKrediet(krediet);
        setReservaties(reservaties);
        setBestellingVerzamelingen(bestellingVerzamelingen);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !validEmail(email))
            throw new IllegalArgumentException("email mag niet null zijn en moet valid email zijn");
        this.email = email;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isBlank() || naam.length() > 25)
            throw new IllegalArgumentException("naam mag niet null of leeg of langer dan 25 characters zijn");
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isBlank() || voornaam.length() > 25)
            throw new IllegalArgumentException("voornaam mag niet null of leeg of langer dan 25 characters zijn");
        this.voornaam = voornaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        if (wachtwoord == null || wachtwoord.length() != 60)
            throw new IllegalArgumentException("wachtwoord mag niet null of moet 60 characters lang zijn");
        this.wachtwoord = wachtwoord;
    }

    public double getKrediet() {
        return krediet;
    }

    public void setKrediet(double krediet) {
        if (krediet < 0) throw new IllegalArgumentException("krediet mag niet negatief zijn");
        this.krediet = krediet;
    }

    public List<Reservatie> getReservaties() {
        return reservaties;
    }

    public void setReservaties(List<Reservatie> reservaties) {
        if (reservaties == null) throw new IllegalArgumentException("reservaties mag niet null zijn");
        this.reservaties = reservaties;
    }

    public List<BestellingVerzameling> getBestellingVerzamelingen() {
        return bestellingVerzamelingen;
    }

    public void setBestellingVerzamelingen(List<BestellingVerzameling> bestellingVerzamelingen) {
        if (bestellingVerzamelingen == null)
            throw new IllegalArgumentException("bestellingverzamelingen mag niet null zijn");
        this.bestellingVerzamelingen = bestellingVerzamelingen;
    }

    public String getRole() {
        return "ROLE_KLANT";
    }

    @Override
    public String toString() {
        return "Klant{" +
                "email='" + email + '\'' +
                ", naam='" + naam + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", krediet=" + krediet +
                ", reservaties=" + reservaties +
                ", bestellingVerzamelingen=" + bestellingVerzamelingen +
                '}';
    }
}
