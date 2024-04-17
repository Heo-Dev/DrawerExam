package kr.benefitplus.drawerexam.comm;

import kr.benefitplus.drawerexam.DTO.AccountInfoRes;
import kr.benefitplus.drawerexam.DTO.SeyfertListRes;
import kr.benefitplus.drawerexam.DTO.VerifyInfoRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MypageAPI {

    // 가상계좌 입출금내역 조회
    @GET("mypage/getSeyfertList")
    Call<SeyfertListRes> getSeyfertList(@Header("Authorization") String token,
                                        @Query("inquiry_tp") String inquiryTp,
                                        @Query("inquiry_year") String inquiryYear,
                                        @Query("inquiry_month") String inquiryMonth,
                                        @Query("inquiry_from_dt") String inquiryFromDt,
                                        @Query("inquiry_to_dt") String inquiryToDt);

    @GET("mypage/getVerifyInfo")
    Call<VerifyInfoRes> getVerifyInfo(@Header("Authorization") String token);

    @GET("mypage/getAccountInfo")
    Call<AccountInfoRes> getAccountInfo(@Header("Authorization") String token);
}
