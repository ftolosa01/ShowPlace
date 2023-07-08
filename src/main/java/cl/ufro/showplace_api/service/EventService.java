package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.model.Booking;
import cl.ufro.showplace_api.model.Customer;
import cl.ufro.showplace_api.model.Event;
import cl.ufro.showplace_api.repository.BookingRepository;
import cl.ufro.showplace_api.repository.CustomerRepository;
import cl.ufro.showplace_api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * save a event of database
     * @param {long} id of Publication
     * @param {Event} event
     */
    public void saveEvent(long id, Event event){
        Optional<Booking> booking = bookingRepository.findById(id);
        booking.get().setEvent(event);
        bookingRepository.save(booking.get());
    }

    /**
     * cancel a event of database
     * @param {long} id of event
     */
    public void saveCancelEvent(long id){
        Optional<Event> event = eventRepository.findById(id);
        event.get().setConfirmedEvent(false);
        eventRepository.save(event.get());

    }

    /**
     * join a event of database
     * @param {long} id of event
     * @param {Customer} customer that binds to the event
     */
    public void joinEvent(long id, Customer customer){

        Optional<Event> event = eventRepository.findById(id);
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());

        event.get().getCustomer().add(customer1.get());
        customer1.get().getEvent().add(event.get());

        customerRepository.save(customer1.get());
        eventRepository.save(event.get());
    }

    /**
     * cancel participation to event
     * @param {long} id of event
     * @param {Customer} customer canceling their participation in the event
     */
    public void cancelParticipationInEvent(long id, Customer customer){
        Optional<Event> event = eventRepository.findById(id);
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());

        event.get().getCustomer().remove(customer1.get());
        customer1.get().getEvent().remove(event.get());

        customerRepository.save(customer1.get());
        eventRepository.save(event.get());
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }



}
