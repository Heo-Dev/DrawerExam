package kr.benefitplus.drawerexam.DTO;

public class PrivateMemberRegisterReq {
    private String email;
    private String password;
    private String password_confirmation;
    private String member_type;
    private String name;
    private String name_en;
    private String phone;
    private String ci;
    private String di;
    private String agreement1;                      // 개인정보 취급방침 및 이용약관 - 개인필수
    private String agreement1_1;                    // 개인정보 수집·이용 - 개인필수
    private String agreement2;                      // 마케팅 정보 수집 및 활용 - 개인선택
    private String agreement3;                      // 리워드 발송을 위한 개인정보 제3자 제공  - 개인선택

    public PrivateMemberRegisterReq(String email, String password, String password_confirmation, String member_type, String name, String name_en, String phone, String ci, String di, String agreement1, String agreement1_1, String agreement2, String agreement3) {
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.member_type = member_type;
        this.name = name;
        this.name_en = name_en;
        this.phone = phone;
        this.ci = ci;
        this.di = di;
        this.agreement1 = agreement1;
        this.agreement1_1 = agreement1_1;
        this.agreement2 = agreement2;
        this.agreement3 = agreement3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getAgreement1() {
        return agreement1;
    }

    public void setAgreement1(String agreement1) {
        this.agreement1 = agreement1;
    }

    public String getAgreement1_1() {
        return agreement1_1;
    }

    public void setAgreement1_1(String agreement1_1) {
        this.agreement1_1 = agreement1_1;
    }

    public String getAgreement2() {
        return agreement2;
    }

    public void setAgreement2(String agreement2) {
        this.agreement2 = agreement2;
    }

    public String getAgreement3() {
        return agreement3;
    }

    public void setAgreement3(String agreement3) {
        this.agreement3 = agreement3;
    }
}
