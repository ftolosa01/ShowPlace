package cl.ufro.showplace_api.repository;

import cl.ufro.showplace_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    <S extends Customer> S save(S entity);

    boolean findByUsername(String username);

    String findByPassword(String password);

    boolean existsByUsername(String username);
    Customer findByEmail(String email);

    Optional<Customer>findOneByEmail(String email);
}
