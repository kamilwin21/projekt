package pl.kul.projekt.appointment.mailer.googleauth.client;

import feign.Param;
import feign.RequestLine;
import pl.kul.projekt.appointment.mailer.googleauth.AccessToken;

public interface GoogleOAuthClient {

    @RequestLine("POST ?client_id={clientId}&client_secret={clientSecret}&refresh_token={refreshToken}&grant_type=refresh_token")
    AccessToken getToken(
            @Param("client_id") String clientId,
            @Param("client_secret") String clientSecret,
            @Param("refresh_token") String refreshToken
    );
}
