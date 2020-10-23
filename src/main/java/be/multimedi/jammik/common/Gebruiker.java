package be.multimedi.jammik.common;

import be.multimedi.jammik.entities.Klant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class Gebruiker implements UserDetails {


    private String naam;
    private String voornaam;

    @JsonIgnore
    private String paswoord;

    public Gebruiker(Klant klant) {


        this.voornaam = klant.getVoornaam();
        this.naam = klant.getNaam();
        this.paswoord = klant.getWachtwoord();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return paswoord;
    }

    @Override
    public String getUsername() {
        return voornaam + naam;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}