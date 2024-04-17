package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class LoginData {
        public User user;
        @SerializedName("seyfert_member") public boolean seyfertMember;
        public Verification verification;
        public Token token;
}
