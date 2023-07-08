package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.model.Qualification;
import cl.ufro.showplace_api.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ClassificationController {
    @Autowired
    private QualificationService qualificationService;

    @PostMapping("/qualify-publication/{id}")
    @ResponseBody
    public void qualifyPlace(@PathVariable Long id,@RequestBody Qualification qualification) {
        qualificationService.saveQualification(qualification, id);
    }

    @GetMapping("/qualify-publication/{id}")
    @ResponseBody
    public double qualifyPublication(@PathVariable Long id) {
        return qualificationService.qualifyPublication(id);
    }


}
