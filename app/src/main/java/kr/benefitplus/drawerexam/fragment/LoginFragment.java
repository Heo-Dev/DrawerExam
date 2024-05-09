package kr.benefitplus.drawerexam.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kr.benefitplus.drawerexam.DTO.LoginReq;
import kr.benefitplus.drawerexam.DTO.LoginRes;
import kr.benefitplus.drawerexam.MainActivity;
import kr.benefitplus.drawerexam.R;
import kr.benefitplus.drawerexam.comm.AuthAPI;
import kr.benefitplus.drawerexam.comm.NetworkClient;
import kr.benefitplus.drawerexam.lib.BplusLib;
import kr.benefitplus.drawerexam.lib.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment  extends Fragment {

    private EditText email;
    private EditText password;
    public Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("LoginFragment", "email = " + email.getText().toString());
                login(email.getText().toString(), password.getText().toString());
            }
        });


        return view;
    }

    public void login (String email, String password){

        BplusLib bplusLib = new BplusLib(getActivity());
        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        AuthAPI authAPI = retrofit.create(AuthAPI.class);

        LoginReq loginReq = new LoginReq(email, password);
        Call<LoginRes> call = authAPI.login(loginReq);
        call.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {

                LoginRes loginRes = response.body();
                if (response.isSuccessful()){

                    Log.d("Login Success ", loginRes.data.token.accessToken);
                    SessionManager sessionManager = new SessionManager(getActivity());
                    sessionManager.saveAuthToken(loginRes.data.token);

                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.removeFragment();

                    // getSeyfertList(1, 0, 0, "", "");
                    // getVerifyInfo();

                }else{
                    String responseBodyString = response.errorBody().toString();
                    Log.d("Login Success ", responseBodyString);
                    if (loginRes.result) {

                        Log.d("Login Success ", String.valueOf(loginRes.result));
                        SessionManager sessionManager = new SessionManager(getActivity());
                        sessionManager.saveAuthToken(loginRes.data.token);

                        MainActivity mainActivity = (MainActivity)getActivity();
                        mainActivity.removeFragment();

                    }else{
                        BplusLib.msgDialog("로그인", loginRes.resultMsg);
                        Log.d("Login false ", loginRes.resultMsg);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                BplusLib.msgDialog("로그인", "로그인 오류, 잠시후 다시 진행하세요");
                Log.d ("API", "authAPI.login Failure ..." + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
