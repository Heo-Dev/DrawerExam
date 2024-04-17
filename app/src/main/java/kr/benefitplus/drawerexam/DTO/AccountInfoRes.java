package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class AccountInfoRes {
    @SerializedName("result")
    private boolean result;
    @SerializedName("result_msg")
    private String resultMsg;
    @SerializedName("data")
    public AccountInfo accountInfo;

    // Getters and setters

    public boolean isResult() {
        return result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }
}










