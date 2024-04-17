package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class ErrorRes {
    public boolean result;
    @SerializedName("result_msg") public String resultMsg;
}
