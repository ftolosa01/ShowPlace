package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.model.Customer;
import cl.ufro.showplace_api.model.Event;
import cl.ufro.showplace_api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/save-event/{id}")
    @ResponseBody
    private void saveEvent(@PathVariable long id, @RequestBody Event event) {
        eventService.saveEvent(id, event);
    }

    @PostMapping("/cancel-event/{id}")
    @ResponseBody
    public void cancelLease(@PathVariable long id){
        eventService.saveCancelEvent(id);
    }

    @PostMapping("/join-event/{id}")
    @ResponseBody
    public void jointEvent(@PathVariable long id,@RequestBody Customer customer){
        eventService.joinEvent(id,customer);
    }

    @PostMapping("/cancel-participation-in-event/{id}")
    @ResponseBody
    public void cancelParticipationInEvent(@PathVariable long id,@RequestBody Customer customer){
        eventService.cancelParticipationInEvent(id,customer);

    }

    @GetMapping("/List-event")
    public List<Event> listEvent(){
        return eventService.findAll();

    }






}
