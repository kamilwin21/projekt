package pl.kul.projekt.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationConfig {

    @Value("${client.id}")
    String clientId;

    @Value("${client.secret}")
    String clientSecret;

    @Value("${client.refreshToken}")
    String clientRefreshToken;

    @Value("${client.tokenUrl}")
    String tokenUrl;

    @Value("${email.username}")
    String emailUsername;

}
