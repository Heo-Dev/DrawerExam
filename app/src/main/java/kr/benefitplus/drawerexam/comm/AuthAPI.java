package kr.benefitplus.drawerexam.comm;

import kr.benefitplus.drawerexam.DTO.ApiRes;
import kr.benefitplus.drawerexam.DTO.LoginData;
import kr.benefitplus.drawerexam.DTO.LoginReq;
import kr.benefitplus.drawerexam.DTO.LoginRes;
import kr.benefitplus.drawerexam.DTO.PrivateMemberRegisterReq;
import kr.benefitplus.drawerexam.DTO.PrivateMemberRegisterRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAPI {

    // 로그인
    @POST("user/login")
    Call<LoginRes> login(@Body LoginReq loginReq);

    // 로그아웃
    @POST("user/logout")
    Call<ApiRes> logout(@Header("Authorization") String token);

    // 개인회원가입
    @POST("user/privateMemberRegister")
    Call<PrivateMemberRegisterRes> privateMemberRegister(@Body PrivateMemberRegisterReq privateMemberRegisterReq);

}
