package be.multimedi.jammik.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Gemaakt door Jan
 */
@Entity
@Table(name="Openingsuren")
public class OpeningsUren {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Min(0)
    @Column(name="Id")
    private int id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "openings_uren_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Dag> dagen;

    public OpeningsUren() {
    }

    public OpeningsUren(@Min(0) int id, List<Dag> dagen) {
        setId(id);
        setDagen(dagen);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id < 0) throw new IllegalArgumentException("id kan niet kleiner zijn dan 0");
        this.id = id;
    }

    public List<Dag> getDagen() {
        return dagen;
    }

    public void setDagen(List<Dag> dagen) {
        if(dagen == null) throw new IllegalArgumentException("dagen kan niet null zijn");

        this.dagen = dagen;
    }

    @Override
    public String toString() {
        return "OpeningsUren{" +
                "id=" + id +
                ", dagen=" + dagen +
                '}';
    }
}
