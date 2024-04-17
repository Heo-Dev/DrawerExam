package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class VerifyInfoRes {
    @SerializedName("result")
    public boolean result;

    @SerializedName("result_msg")
    public String resultMsg;

    @SerializedName("data")
    public Verification data;
}
