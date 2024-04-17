package kr.benefitplus.drawerexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import kr.benefitplus.drawerexam.DTO.ApiRes;
import kr.benefitplus.drawerexam.DTO.LoginRes;
import kr.benefitplus.drawerexam.adapter.MainViewPager2Adapter;
import kr.benefitplus.drawerexam.comm.AuthAPI;
import kr.benefitplus.drawerexam.comm.NetworkClient;
import kr.benefitplus.drawerexam.fragment.LoginFragment;
import kr.benefitplus.drawerexam.fragment.MainFragment;
import kr.benefitplus.drawerexam.fragment.SeyfertListFragment;
import kr.benefitplus.drawerexam.lib.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 햄버거 버튼 이미지 불러오기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);


        // main fragment
        MainFragment mainFragment = new MainFragment();
        replaceFragment(mainFragment);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.invest:
                        break;

                    case R.id.seyfertList:
                        SeyfertListFragment seyfertListFragment = new SeyfertListFragment();
                        replaceFragmentFull(seyfertListFragment);
                        break;
                    case R.id.logout:
                        logoutProcess();
                        drawerLayout.closeDrawers();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        ImageView drawerClose = headerView.findViewById(R.id.drawer_close);
        drawerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });


        Button loginButton = headerView.findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 로그인 fragment
                LoginFragment loginFragment = new LoginFragment();
                replaceFragment(loginFragment);
                drawerLayout.closeDrawers();

            }
        });
        Button registButton = headerView.findViewById(R.id.registMember);
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 fragment
            }
        });

        Button logoutButton = headerView.findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그아웃
                logoutProcess();
                drawerLayout.closeDrawers();
            }
        });

        Button mypageButton = headerView.findViewById(R.id.mypage);
        mypageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 마이페이지
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {       // 왼쪽 상단 버튼 눌렀을 때

                // 로그인 토큰 확인
                View headerView = navigationView.getHeaderView(0);
                LinearLayout beforeLoginLayout = headerView.findViewById(R.id.beforeLoginLayout);
                LinearLayout afterLoginLayout = headerView.findViewById(R.id.afterLoginLayout);

                SessionManager sessionManager = new SessionManager(this);
                if (sessionManager.isSession()){
                    beforeLoginLayout.setVisibility(View.GONE);
                    afterLoginLayout.setVisibility(View.VISIBLE);
                }else{
                    beforeLoginLayout.setVisibility(View.VISIBLE);
                    afterLoginLayout.setVisibility(View.GONE);
                }

                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //뒤로가기 했을 때
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Fragment를 교체하는 메소드
    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // Fragment 삭제하기
    public void removeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    // Fragment를 교체하는 메소드
    private void replaceFragmentFull(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_layout, fragment);
        fragmentTransaction.commit();
    }

    // Fragment 삭제하기
    public void removeFragmentFull() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.relative_layout);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    public void logoutProcess(){
        Retrofit retrofit = NetworkClient.getRetrofitClient(this);
        AuthAPI authAPI = retrofit.create(AuthAPI.class);

        SessionManager sessionManager = new SessionManager(this);
        Call<ApiRes> call = authAPI.logout("Bearer " + sessionManager.fetchAuthToken());
        call.enqueue(new Callback<ApiRes>() {
            @Override
            public void onResponse(Call<ApiRes> call, Response<ApiRes> response) {
                if (response.isSuccessful()){

                    ApiRes apiRes = response.body();

                    SessionManager sessionManager = new SessionManager(MainActivity.this);
                    sessionManager.logout();
                }
            }

            @Override
            public void onFailure(Call<ApiRes> call, Throwable t) {
                Log.d ("API", "authAPI.logout Failure ..." + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}