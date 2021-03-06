package be.multimedi.jammik.entities;

import com.sun.istack.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

// Gemaakt door: Michael Creelle

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    @Column(name = "Id")
    private int id;

    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Menu_MenuItems",
            joinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_item_id", referencedColumnName = "id")})
    private List<MenuItem> menuItems;

    public Menu() {
    }

    public Menu(@Min(0) int id, List<MenuItem> menuItems) {
        setId(id);
        setMenuItems(menuItems);
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id mag niet kleiner zijn dan 0");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        if (menuItems == null) throw new IllegalArgumentException("MenuItems mag niet null zijn");
        this.menuItems = menuItems;
    }
}
