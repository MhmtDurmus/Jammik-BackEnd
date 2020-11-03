package be.multimedi.jammik.controllers;

import be.multimedi.jammik.entities.Zaak;
import be.multimedi.jammik.repositories.ZaakRepository;
import be.multimedi.jammik.services.ZaakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Made by Koen
 */
@RestController
@RequestMapping("/zaken")
@CrossOrigin
public class ZaakController {

    private ZaakService zaakService;

    private ZaakRepository repository;

    @Autowired
    public void setRepository(ZaakRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setZaakService(ZaakService zaakService) {
        this.zaakService = zaakService;
    }

    @GetMapping(value = "zaak/id={id}", produces="application/json")
    public ResponseEntity<Zaak> getZaakById(@PathVariable("id") int id) {

        return ResponseEntity.ok(repository.getZaakById(id));
    }

    @GetMapping
    public ResponseEntity<List<Zaak>> getAlleZaken() {

        return ResponseEntity.ok(zaakService.getAlleZaken());
    }

    @GetMapping(value = "{email}")
    public ResponseEntity<List<Zaak>> getZakenOpUitbater(@PathVariable("email") String email) {

        return ResponseEntity.ok(repository.findZaaksByEmail(email).orElseThrow());
    }

    @GetMapping(value = "zaak/{naam}", produces="application/json")
    public ResponseEntity<Zaak> getZaakbyNaam(@PathVariable("naam") String zaaknaam) {

        return ResponseEntity.ok(repository.findZaakByNaam(zaaknaam).get());
    }

    @PostMapping(consumes={MediaType.MULTIPART_FORM_DATA_VALUE}, produces="application/json")
    private ResponseEntity<Zaak> postHandler(@RequestParam("zaak") String zaakJsonString,
                                             @RequestParam("imageFile") MultipartFile file) throws Exception {

        Zaak zaak = zaakService.saveZaak(zaakJsonString, file);
        return zaak != null ? ResponseEntity.ok(zaak) : ResponseEntity.badRequest().build();
    }
}
