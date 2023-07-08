package cl.ufro.showplace_api.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
    private String username;
}
