package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountInfo {
    @SerializedName("user_type")
    public String userType;
    @SerializedName("virtual_acct")
    public List<VirtualAccount> virtualAcct;
    @SerializedName("real_acct")
    public List<RealAccount> realAcct;
    @SerializedName("balance")
    public int balance;
    @SerializedName("company_doc_stat")
    public CompanyDocStat companyDocStat;

    // Getters and setters

    public String getUserType() {
        return userType;
    }

    public List<VirtualAccount> getVirtualAcct() {
        return virtualAcct;
    }

    public List<RealAccount> getRealAcct() {
        return realAcct;
    }

    public int getBalance() {
        return balance;
    }

    public CompanyDocStat getCompanyDocStat() {
        return companyDocStat;
    }
}