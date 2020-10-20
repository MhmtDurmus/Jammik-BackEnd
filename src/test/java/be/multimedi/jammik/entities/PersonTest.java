package be.multimedi.jammik.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Gemaakt door: Michael Creelle

class PersonTest {
    private class PersonMock extends Person {}
    private PersonMock personMock;

    @BeforeEach
    void setUp() {
        personMock = new PersonMock();
    }

    @Test
    void set_get_email() {
        String email = "jos@somemail.com";
        personMock.setEmail(email);
        assertEquals(email, personMock.getEmail());
    }

    @Test
    void throws_on_invalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setEmail("1@2.3>6"));
    }

    @Test
    void throws_on_null_email() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setEmail(null));
    }

    @Test
    void set_get_naam() {
        String naam = "jos";
        personMock.setNaam(naam);
        assertEquals(naam, personMock.getNaam());
    }

    @Test
    void throws_on_empty_naam() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setNaam(""));
    }

    @Test
    void throws_on_null_naam() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setNaam(null));
    }

    @Test
    void set_get_voornaam() {
        String voornaam = "jos";
        personMock.setVoornaam(voornaam);
        assertEquals(voornaam, personMock.getVoornaam());
    }

    @Test
    void throws_on_empty_voornaam() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setVoornaam(""));
    }

    @Test
    void throws_on_null_voornaam() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setVoornaam(null));
    }

    @Test
    void setWachtwoord() {
        String ww = "a".repeat(72);
        personMock.setWachtwoord(ww);
        assertEquals(ww, personMock.getWachtwoord());
    }

    @Test
    void throws_on_71long_wachtwoord() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setWachtwoord("a".repeat(71)));
    }

    @Test
    void throws_on_73long_wachtwoord() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setWachtwoord("a".repeat(73)));
    }

    @Test
    void throws_on_null_wachtwoord() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setWachtwoord(null));
    }

    @Test
    void set_get_krediet() {
        int krediet = 15;
        personMock.setKrediet(krediet);
        assertEquals(krediet, personMock.getKrediet());
    }

    @Test
    void throws_on_negative_krediet() {
        assertThrows(IllegalArgumentException.class, () -> personMock.setKrediet(-1));
    }

}