package com.dev.web.blogging.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Getter
@Setter
public class BcryptUtil {

    public static String generateHash(String password, int rounds) {
        return BCrypt.hashpw(password, BCrypt.gensalt(rounds));
    }

}
