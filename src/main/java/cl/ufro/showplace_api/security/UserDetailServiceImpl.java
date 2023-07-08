package cl.ufro.showplace_api.security;

import cl.ufro.showplace_api.model.Customer;
import cl.ufro.showplace_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("el usuario con email " + email + " no existe"));

        return new UserDetailsIpml(customer);
    }
}
