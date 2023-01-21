package pl.kul.projekt.appointment.mailer.googleauth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class AccessToken {

    @SerializedName("access_token")
    private final String accessToken;

}
