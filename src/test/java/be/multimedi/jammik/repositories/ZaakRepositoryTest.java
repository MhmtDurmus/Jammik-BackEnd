package be.multimedi.jammik.repositories;

import be.multimedi.jammik.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Gemaakt door Jan
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ZaakRepositoryTest {

    private ZaakRepository zr;
    private OpeningsUrenRepository our;
    private DagRepository dr;
    private AdresRepository ar;
    private MenuRepository mr;
    private UitbaterRepository ur;
    private TafelRepository tr;
    private ReservatieRepository rr;

    @Autowired
    public ZaakRepositoryTest(ZaakRepository zr, OpeningsUrenRepository our, DagRepository dr, AdresRepository ar, MenuRepository mr,
                              UitbaterRepository ur, TafelRepository tr, ReservatieRepository rr) {
        this.zr = zr;
        this.our = our;
        this.dr = dr;
        this.ar = ar;
        this.mr = mr;
        this.ur = ur;
        this.tr = tr;
        this.rr = rr;
    }

    @Test
    void getById() {
        Zaak zaak = zr.getZaakById(3);
        assertNotNull(zaak);
        assertEquals("Bistro Koen", zaak.getNaam());
    }

    @Test
    void findAll() {
        List<Zaak> zaken = zr.findAll();
        assertNotNull(zaken);
        assertEquals(5, zaken.size());
    }

    @Test
    void save() {
        OpeningsUren ourn = our.getOpeningsUrenById(2);

        Adres adres = ar.getAdresById(1);
        Menu menu = mr.getMenuById(3);
        Uitbater uitbater = ur.findUitbaterByEmail("michael@jammik.be").get();
        List<Tafel> tafels = new ArrayList<>(List.of(tr.getTafelById(2), tr.getTafelById(3)));
        List<Reservatie> reservaties = new ArrayList<>(List.of(rr.getReservatieById(1), rr.getReservatieById(3)));

        Zaak zaak = new Zaak(0, "TestBistro", false, 2.6f, ourn, adres, menu, "michael@jammik.be", tafels, reservaties);

        zaak = zr.save(zaak);
        assertEquals(6, zaak.getId());
    }

    @Test
    void deleteById() {
        zr.deleteById(4);
        assertEquals(4, zr.findAll().size());
    }
}
