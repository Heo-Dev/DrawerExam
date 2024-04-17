package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    public String name;
    @SerializedName("name_en")
    private String nameEn;
    private String email;
    private String password;
    @SerializedName("seyfert_id")
    private String seyfertId;
    @SerializedName("borrower_seyfert_id")
    private String borrowerSeyfertId;
    @SerializedName("virtual_account_type")
    private String virtualAccountType;
    @SerializedName("investor_type")
    private int investorType;
    @SerializedName("borrower_type")
    private String borrowerType;
    @SerializedName("auth_type")
    private String authType;
    private String avatar;
    private String di;
    private String ci;
    @SerializedName("mywork_postcode")
    private String myworkPostcode;
    @SerializedName("mywork_address2")
    private String myworkAddress2;
    @SerializedName("mywork_address1")
    private String myworkAddress1;
    @SerializedName("mywork_phone")
    private String myworkPhone;
    @SerializedName("mywork_address")
    private String myworkAddress;
    @SerializedName("mywork_name")
    private String myworkName;
    @SerializedName("mywork_category")
    private String myworkCategory;
    @SerializedName("account_type")
    private String accountType;
    @SerializedName("account_number")
    private String accountNumber;
    @SerializedName("account_owner")
    private String accountOwner;
    @SerializedName("company_representative")
    private String companyRepresentative;
    @SerializedName("company_representative_en")
    private String companyRepresentativeEn;
    @SerializedName("company_registration_no")
    private String companyRegistrationNo;
    @SerializedName("company_docu_submit")
    private int companyDocuSubmit;
    @SerializedName("manager_name")
    private String managerName;
    @SerializedName("manager_phone")
    private String managerPhone;
    @SerializedName("manager_email")
    private String managerEmail;
    @SerializedName("remember_token")
    private String rememberToken;
    @SerializedName("ads_allowed_at")
    private String adsAllowedAt;
    @SerializedName("investment_letter_allowed_at")
    private String investmentLetterAllowedAt;
    @SerializedName("login_at")
    private String loginAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("deleted_at")
    private String deletedAt;
    @SerializedName("confirmed_at")
    private String confirmedAt;
    @SerializedName("pwchanged_at")
    private String pwchangedAt;
    @SerializedName("resting_email_send_at")
    private String restingEmailSendAt;
    private String isRest;
    @SerializedName("reward_allowed_at")
    private String rewardAllowedAt;
    @SerializedName("investor_info_allowed_at")
    private String investorInfoAllowedAt;
    @SerializedName("manager_info_allowed_at")
    private String managerInfoAllowedAt;
    @SerializedName("investor_work_info_allowed_at")
    private String investorWorkInfoAllowedAt;
    @SerializedName("account_info_allowed_at")
    private String accountInfoAllowedAt;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSeyfertId() {
        return seyfertId;
    }

    public String getBorrowerSeyfertId() {
        return borrowerSeyfertId;
    }

    public String getVirtualAccountType() {
        return virtualAccountType;
    }

    public int getInvestorType() {
        return investorType;
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public String getAuthType() {
        return authType;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDi() {
        return di;
    }

    public String getCi() {
        return ci;
    }

    public String getMyworkPostcode() {
        return myworkPostcode;
    }

    public String getMyworkAddress2() {
        return myworkAddress2;
    }

    public String getMyworkAddress1() {
        return myworkAddress1;
    }

    public String getMyworkPhone() {
        return myworkPhone;
    }

    public String getMyworkAddress() {
        return myworkAddress;
    }

    public String getMyworkName() {
        return myworkName;
    }

    public String getMyworkCategory() {
        return myworkCategory;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public String getCompanyRepresentative() {
        return companyRepresentative;
    }

    public String getCompanyRepresentativeEn() {
        return companyRepresentativeEn;
    }

    public String getCompanyRegistrationNo() {
        return companyRegistrationNo;
    }

    public int getCompanyDocuSubmit() {
        return companyDocuSubmit;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public String getAdsAllowedAt() {
        return adsAllowedAt;
    }

    public String getInvestmentLetterAllowedAt() {
        return investmentLetterAllowedAt;
    }

    public String getLoginAt() {
        return loginAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public String getConfirmedAt() {
        return confirmedAt;
    }

    public String getPwchangedAt() {
        return pwchangedAt;
    }

    public String getRestingEmailSendAt() {
        return restingEmailSendAt;
    }

    public String getIsRest() {
        return isRest;
    }

    public String getRewardAllowedAt() {
        return rewardAllowedAt;
    }

    public String getInvestorInfoAllowedAt() {
        return investorInfoAllowedAt;
    }

    public String getManagerInfoAllowedAt() {
        return managerInfoAllowedAt;
    }

    public String getInvestorWorkInfoAllowedAt() {
        return investorWorkInfoAllowedAt;
    }

    public String getAccountInfoAllowedAt() {
        return accountInfoAllowedAt;
    }
}
