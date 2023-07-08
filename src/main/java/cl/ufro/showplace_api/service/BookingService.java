package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.model.Booking;
import cl.ufro.showplace_api.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * save a booking of database
     * @param {Booking} booking
     */
    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }

    public Optional<Booking> findBooking(long id){
       return bookingRepository.findById(id);
    }



}
