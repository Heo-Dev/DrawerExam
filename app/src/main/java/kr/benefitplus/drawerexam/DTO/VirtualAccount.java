package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class VirtualAccount {
    @SerializedName("bank_name")
    public String bankName;
    @SerializedName("bank_code")
    public String bankCode;
    @SerializedName("account_number")
    public String accountNumber;
    @SerializedName("customer_name")
    public String customerName;

    // Getters and setters

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }
}