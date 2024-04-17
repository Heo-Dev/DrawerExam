package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class Verification {
    @SerializedName("verify_acct_stat") public boolean verifyAcctStat;
    @SerializedName("verify_acct_msg") public String verifyAcctMsg;
    @SerializedName("verify_acct_at") public String verifyAcctAt;
    @SerializedName("verify_identity_stat") public boolean verifyIdentityStat;
    @SerializedName("verify_identity_msg") public String verifyIdentityMsg;
    @SerializedName("verify_identity_at") public String verifyIdentityAt;
    @SerializedName("verify_photoupload_at") public String verifyPhotouploadAt;
    @SerializedName("verify_identity_expire") public String verifyIdentityExpire;
    @SerializedName("verify_identity_expire_stat") public boolean verifyIdentityExpireStat;
    @SerializedName("verify_next") public String verifyNext;
}
