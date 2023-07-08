package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.model.Booking;
import cl.ufro.showplace_api.model.Publication;
import cl.ufro.showplace_api.repository.PublicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    /**
     * save a publication of database
     *
     * @param {Publication} publication
     */
    public void save(Publication publication) {
        publicationRepository.save(publication);
    }

    /**
     * find a Publication of database
     *
     * @param {long} id of Publication
     * @return {Publication} respond with the publication
     */
    public Optional<Publication> findById(long id) {
        return publicationRepository.findById(id);
    }

    /**
     * find a Publication of database
     *
     * @param {long} id of Publication
     * @return {List<Publication>} respond with all publications
     */
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }


    /**
     * @param id id of a Publication to generate a profit report
     * @return a report of the profit of a Publication (for now a String)
     */
    public String generateProfitReport(long id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        String report = "";
        int totalProfit = 0;
        if (publication.isPresent()) {
            report += "Profit report for publication " + publication.get().getName() + "\n";
            List<Booking> bookings = publication.get().getBooking();
            for (Booking booking : bookings) {
                int payment = Integer.parseInt(booking.getPay().getPrice());
                int profit = 0;
                profit += payment;
                report += "Booking id: " + booking.getId() + "\n";
                report += "Payment: " + payment + "\n";
                totalProfit += profit;
            }
            report += "Total profit: " + totalProfit;
        } else {
            report += "Publication not found\n Payment: " + totalProfit;
        }
        return report;
    }
}
