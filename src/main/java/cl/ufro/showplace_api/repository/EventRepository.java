package cl.ufro.showplace_api.repository;

import cl.ufro.showplace_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
}
