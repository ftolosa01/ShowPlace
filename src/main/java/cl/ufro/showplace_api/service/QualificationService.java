package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.model.Publication;
import cl.ufro.showplace_api.model.Qualification;
import cl.ufro.showplace_api.repository.QualificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Service
@Transactional
public class QualificationService {
    @Autowired
    private PublicationService publicationService;

    /**
     * save a qualification of database
     * @param {Qualification} object of qualification
     * @param {long} id of place
     */
    public void saveQualification(Qualification qualification, long id) {
        Optional<Publication> publication = publicationService.findById(id);
        List<Qualification> qualificationList = publication.get().getQualification();
        qualificationList.add(qualification);

        publication.get().setQualification(qualificationList);

        publicationService.save(publication.get());
    }

    /**
     * find a qualification of database
     * @param {long} id of Publication
     * @return {double} responds with the qualification
     */
    public double qualifyPublication(long id) {
        double qualification = 0;
        Optional<Publication> publication = publicationService.findById(id);
        List<Qualification> qualificationList = publication.get().getQualification();
        for (int i = 0; i < qualificationList.size(); i++) {
            qualification = qualification + qualificationList.get(i).getQualification();
        }
        qualification = qualification / qualificationList.size();
        return qualification;
    }

}
