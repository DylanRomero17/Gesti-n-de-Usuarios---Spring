package adea.adea.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Base64;

public class ShaBase64PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // Codificar la contraseña en Base64
        return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Codificar la contraseña proporcionada en Base64 y compararla con la codificada
        String encodedRawPassword = Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
        return encodedRawPassword.equals(encodedPassword);
    }
}
