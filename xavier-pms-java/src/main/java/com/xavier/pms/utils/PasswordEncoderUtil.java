package com.xavier.pms.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Slf4j
public class PasswordEncoderUtil {
    private final Pattern BCRYPT_PATTERN = Pattern
            .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    public String encode(CharSequence rawPassword) {
        if (StrUtil.isNotEmpty(rawPassword)) {
            String salt = BCrypt.gensalt();
            return BCrypt.hashpw(rawPassword.toString(), salt);
        }
        return null;
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.warn("Empty encoded password");
            return false;
        }

        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            log.warn("Encoded password does not look like BCrypt");
            return false;
        }

        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }

}
