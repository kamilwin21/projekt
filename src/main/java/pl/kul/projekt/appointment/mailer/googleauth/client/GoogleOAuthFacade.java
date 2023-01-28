package pl.kul.projekt.appointment.mailer.googleauth.client;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kul.projekt.appointment.mailer.googleauth.AccessToken;
import pl.kul.projekt.config.ApplicationConfig;

@Service
@RequiredArgsConstructor
public class GoogleOAuthFacade {

    private final ApplicationConfig applicationConfig;

    private GoogleOAuthClient client() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(GoogleOAuthClient.class, applicationConfig.getTokenUrl());
    }

    public AccessToken getToken() {
        return client().getToken(
                applicationConfig.getClientId(),
                applicationConfig.getClientSecret(),
                applicationConfig.getClientRefreshToken());
    }
}
