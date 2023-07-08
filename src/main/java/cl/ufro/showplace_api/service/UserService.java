package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.model.Customer;
import cl.ufro.showplace_api.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * save a new user customer
     *
     * @param {customer} object of user
     */
    public void save(Customer customer) throws NoSuchPaddingException, IllegalBlockSizeException,
            UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String encryptPassword = this.passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptPassword);
        customerRepository.save(customer);
    }

    /**
     * find user customer of database
     *
     * @param {customer} object of user
     * @return {boolean} Response true with the user is find, and null when the user is not find
     */

    /**
     * Check if email is available
     *
     * @param email email to check
     * @return true if email is available, false if not
     */
    public boolean isEmailAvailable(String email) {
        return customerRepository.findByEmail(email) == null;
    }

    /**
     * Check if username is available
     *
     * @param username username to check
     * @return true if username is available, false if not
     */
    public boolean isUsernameAvailable(String username) {
        return !customerRepository.existsByUsername(username);
    }

    public boolean singIn(Customer customer) {
        Optional<Customer> customer1 = customerRepository.findOneByEmail(customer.getEmail());
        if (customer1.get().getEmail().equals(customer.getEmail())) {
            if (passwordEncoder.matches(customer.getPassword(), customer1.get().getPassword())) {
                return true;
            }
        }
        return false;
    }

    /*public String generarToken(String usuario) {
        long tiempoExpiracion = 3600000; // 1 hora en milisegundos
        String claveSecreta = "TokenSuperSecretoParaUtilizarEnCustomerInovacion"; // Cambia esto por tu propia clave secreta

        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + tiempoExpiracion);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(SignatureAlgorithm.HS256, claveSecreta);

        return jwtBuilder.compact();
    }*/

    /*public Customer findByCustomer(Customer customer){
        return customerRepository.findByEmail(customer.getEmail());
    }*/
}
