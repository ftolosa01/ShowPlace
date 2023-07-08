package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.model.Booking;
import cl.ufro.showplace_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private BookingService bookingService;

    @PutMapping("/cancel_lease/{id}")
    public void cancelLease(@PathVariable long id){
        Optional<Booking> booking1 = bookingService.findBooking(id);
        booking1.get().setConfirmedReservation(false);
        bookingService.saveBooking(booking1.get());
    }

    @PostMapping("/reserve-place")
    public void reservePlace(@RequestBody Booking booking){
        bookingService.saveBooking(booking);
    }

}
