package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.service.AvailabilityService;
import cl.ufro.showplace_api.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;
    @Autowired
    private PublicationService publicationService;

    @GetMapping("/place-availability/{id}")
    @ResponseBody
    public List<Object> visualizePlace(@PathVariable long id) {
        return availabilityService.calculateAvailability(id);
    }

}
