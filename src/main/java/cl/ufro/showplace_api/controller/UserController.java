package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.model.Customer;
import cl.ufro.showplace_api.security.TokenUtils;
import cl.ufro.showplace_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String appStart() {
        return "Bienvenido a ShowPlace";
    }


    @PostMapping("/Sign-Up")
    public void singUp(@RequestBody Customer customer) throws NoSuchPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        userService.save(customer);
    }

    @PostMapping("/Sign-in")
    public boolean singIn(@RequestBody Customer customer){
        if (userService.singIn(customer)){
            TokenUtils.creteToken(customer.getUsername(),customer.getEmail());
            return true;
        }
        return false;
    }
    /**
     * Check if email is available
     * @param email email to check
     * @return true if email is available, false if not
     */

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<Boolean> checkEmailAvailability(@RequestParam("email") String email) {
        boolean isEmailAvailable = userService.isEmailAvailable(email);
        return ResponseEntity.ok(isEmailAvailable);
    }

    /**
     * Check if username is available
     * @param username username to check
     * @return true if username is available, false if not
     */
    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam("username") String username) {
        boolean isUsernameAvailable = userService.isUsernameAvailable(username);
        return ResponseEntity.ok(isUsernameAvailable);
    }


}
