package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.model.Publication;
import cl.ufro.showplace_api.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @PostMapping("/Publication")
    public void publicationPlace(@RequestBody Publication publication) {
        publicationService.save(publication);
    }

    @GetMapping("/list-publication")
    public List<Publication> publicationList() {
        return publicationService.findAll();
    }

    @GetMapping("/list-publication-nearby")
    public List<Publication> publicationListNearby() {
        return null;
    }

    @GetMapping("/publication/{id}")
    @ResponseBody
    public Optional<Publication> specificPublication(@PathVariable long id) {
        return publicationService.findById(id);
    }

    /**
     * Generates a profit report for a specific Publication
     *
     * @param id id of a Publication to generate a profit report
     */
    @GetMapping("/{id}/report")
    public ResponseEntity<String> reportPublication(@PathVariable long id) {
        String report = publicationService.generateProfitReport(id);
        if (report.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
