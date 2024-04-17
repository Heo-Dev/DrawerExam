package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class RealAccount {
    @SerializedName("bankNm")
    private String bankName;
    @SerializedName("acctNo")
    private String accountNumber;
    @SerializedName("acctNm")
    private String accountName;
    @SerializedName("varify")
    private String verify;

    // Getters and setters

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getVerify() {
        return verify;
    }
}