package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.model.Availability;
import cl.ufro.showplace_api.model.Publication;
import cl.ufro.showplace_api.repository.AvailabilityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AvailabilityService {
    @Autowired
    private PublicationService publicationRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    /**
     * calculate availability of one publication
     *
     * @param {List<AvailableDate>}
     * @return {List<AvailableDate>} Returns list of availability
     */
    public List<Object> calculateAvailability(long id) {
        Optional<Publication> publication1 = publicationRepository.findById(id);
        List<Availability> availabilityList = new ArrayList<>();

        if (publication1.get().getBooking().isEmpty()) {
            availabilityList.add(publication1.get().getAvailability());
            return Collections.singletonList(availabilityList);
        } else {
            return findUnavailableDates(id);
        }
    }

    public List<Object> findUnavailableDates(long id) {
        Optional<Publication> publication1 = publicationRepository.findById(id);
        List<LocalDateTime> availabilityList = new ArrayList<>();

        if (publication1.get().getBooking().get(0).getBookingStart()
                .isAfter(publication1.get().getAvailability().getAvailabilityStart())) {
            if (publication1.get().getBooking().get(0).getBookingEnd()
                    .isBefore(publication1.get().getAvailability().getAvailabilityEnd())) {
                availabilityList.add(publication1.get().getAvailability().getAvailabilityStart());
                availabilityList.add(publication1.get().getBooking().get(0).getBookingStart());

                availabilityList.add(publication1.get().getBooking().get(0).getBookingEnd());
                availabilityList.add(publication1.get().getAvailability().getAvailabilityEnd());
                return Collections.singletonList(availabilityList);
            }
        }

        return null;
    }
}
