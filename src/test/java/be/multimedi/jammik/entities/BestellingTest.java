package be.multimedi.jammik.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

// Gemaakt door: Michael Creelle

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BestellingTest {

    private Bestelling bestelling;

    @BeforeEach
    void setUp() {
        bestelling = new Bestelling();
    }

    @Test
    void set_get_id() {
        int id = 5;
        bestelling.setId(id);
        assertEquals(id, bestelling.getId());
    }

    @Test
    void throw_exception_on_negative_id(){
        assertThrows(IllegalArgumentException.class, () -> bestelling.setId(-1));
    }

    @Test
    void set_get_MenuItems() {
        MenuItem menuItem = new MenuItem();
        bestelling.setMenuItem(menuItem);
        assertEquals(menuItem, bestelling.getMenuItem());
    }

    @Test
    void throws_exception_on_null_menuItem() {
        assertThrows(IllegalArgumentException.class, () -> bestelling.setMenuItem(null));
    }

    @Test
    void set_get_zaakId() {
        int zaakId = 15;
        bestelling.setZaakId(zaakId);
        assertEquals(zaakId, bestelling.getZaakId());
    }

    @Test
    void throws_on_zaak_onder_1(){
        assertThrows(IllegalArgumentException.class, () -> bestelling.setZaakId(0));
    }

    @Test
    void set_get_bestelling_verzameling_id() {
        bestelling.setBestellingVerzamelingId(1);
        assertEquals(1, bestelling.getBestellingVerzamelingId());
    }

    @Test
    void throws_on_bestelling_verzameling_id_onder_0(){
        assertThrows(IllegalArgumentException.class, () -> bestelling.setBestellingVerzamelingId(-1));
    }

    @Test
    void set_get_aantal(){
        bestelling.setAantal(5);
        assertEquals(5, bestelling.getAantal());
    }

    @Test
    void throws_on_aantal_less_than_1() {
        assertThrows(IllegalArgumentException.class, () -> bestelling.setAantal(0));
    }

}