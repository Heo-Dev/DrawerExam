package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("token_type") public String tokenType;
    @SerializedName("expires_in") public int expiresIn;
    @SerializedName("access_token") public String accessToken;
    @SerializedName("refresh_token") public String refreshToken;
}
