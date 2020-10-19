package be.multimedi.jammik.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

// Gemaakt door: Michael Creelle

@Entity
@Table(name = "BestellingVerzameling")
public class BestellingVerzameling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToMany
    @Column(name = "Bestellingen")
    private List<Bestelling> bestellingen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 1) throw new IllegalArgumentException("id mag niet 0 of negatief zijn");
        this.id = id;
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        if (bestellingen == null || bestellingen.size() < 1) throw new IllegalArgumentException("bestellingen mag niet null of leeg zijn");
        this.bestellingen = bestellingen;
    }
}
