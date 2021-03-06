package be.multimedi.jammik.entities;

import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Gemaakt door: Michael Creelle

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BestellingVerzamelingTest {

    private BestellingVerzameling bestellingVerzameling;

    @BeforeEach
    void setUp() {
        bestellingVerzameling = new BestellingVerzameling();
    }

    @Test
    void set_get_id() {
        int id = 5;
        bestellingVerzameling.setId(id);
        assertEquals(id, bestellingVerzameling.getId());
    }

    @Test
    void throw_exception_on_negative_id(){
        assertThrows(IllegalArgumentException.class, () -> bestellingVerzameling.setId(-1));
    }


    @Test
    void set_get_bestellingen() {
        List<Bestelling> bestellingen = new ArrayList<>();
        bestellingen.add(new Bestelling());
        bestellingVerzameling.setBestellingen(bestellingen);
        assertEquals(bestellingen, bestellingVerzameling.getBestellingen());
    }

    @Test
    void throws_exception_on_null_menuItems() {
        assertThrows(IllegalArgumentException.class, () -> bestellingVerzameling.setBestellingen(null));
    }

    @Test
    void throws_exception_on_empty_list_menuItems() {
        assertThrows(IllegalArgumentException.class, () -> bestellingVerzameling.setBestellingen(new ArrayList<>()));
    }

    @Test
    void set_get_Klant(){
        String klant = "jos@vos.com";
        bestellingVerzameling.setKlant(klant);
        assertEquals( klant, bestellingVerzameling.getKlant());
    }

    @Test
    void throw_on_klant_null(){
        assertThrows(IllegalArgumentException.class, () -> bestellingVerzameling.setKlant(null));
    }
}