package kr.benefitplus.drawerexam.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.benefitplus.drawerexam.DTO.AccountInfoRes;
import kr.benefitplus.drawerexam.DTO.SeyfertListRes;
import kr.benefitplus.drawerexam.MainActivity;
import kr.benefitplus.drawerexam.R;
import kr.benefitplus.drawerexam.adapter.SeyfertListAdapter;
import kr.benefitplus.drawerexam.comm.MypageAPI;
import kr.benefitplus.drawerexam.comm.NetworkClient;
import kr.benefitplus.drawerexam.lib.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeyfertListFragment extends Fragment implements InquiryCondBottomSheetFragment.BottomSheetListener {

    private SeyfertListRes seyfertListRes;
    private AccountInfoRes accountInfoRes;
    private SeyfertListAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private View view;

    private TextView virualBankNm;
    private TextView virualAcctNm;
    private TextView virualAcctNo;

    private TextView bankNm;
    private TextView acctNm;
    private TextView acctNo;
    private TextView balance;

    private LinearLayout condLayout;
    private TextView inquiryCond;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // view = inflater.inflate(R.layout.seyfertlist, container, false);
        view = inflater.inflate(R.layout.account_info, container, false);

        virualBankNm = view.findViewById(R.id.virtual_bank_nm);
        virualAcctNm = view.findViewById(R.id.virtual_acct_nm);
        virualAcctNo = view.findViewById(R.id.virtual_acct_no);

        bankNm = view.findViewById(R.id.bank_nm);
        acctNm = view.findViewById(R.id.acct_nm);
        acctNo = view.findViewById(R.id.acct_no);
        balance = view.findViewById(R.id.balance);

        inquiryCond = view.findViewById(R.id.inquiry_cond);
        inquiryCond.setText(inquiryString(1, 0, 0, "", ""));


        // 돌아가기
        ImageView backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d ("SeyfertListFragment", "backArrow clicked..");
                MainActivity activity = (MainActivity) getActivity();
                activity.removeFragmentFull();
            }
        });


        // 출금
        TextView withdraw = view.findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d ("SeyfertListFragment", "withdraw clicked..");
                InquiryCondBottomSheetFragment inquiryCondBottomSheetFragment = new InquiryCondBottomSheetFragment();
                inquiryCondBottomSheetFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
                inquiryCondBottomSheetFragment.show(getChildFragmentManager(), inquiryCondBottomSheetFragment.getTag());
            }
        });


        // 조회조건
        condLayout = view.findViewById(R.id.cond_layout);
        inquiryCond = view.findViewById(R.id.inquiry_cond);
        condLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d ("SeyfertListFragment", "입출내역 조회조건 clicked..");
                InquiryCondBottomSheetFragment inquiryCondBottomSheetFragment = new InquiryCondBottomSheetFragment();
                inquiryCondBottomSheetFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
                inquiryCondBottomSheetFragment.show(getChildFragmentManager(), inquiryCondBottomSheetFragment.getTag());
            }
        });

        getAccountInfo();
        getSeyfertList(1, 0, 0, "", "");

        return view;
    }

    public void getAccountInfo() {
        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        MypageAPI mypageAPI = retrofit.create(MypageAPI.class);

        SessionManager sessionManager = new SessionManager(getActivity());
        // Log.d("getAccountInfo : TP = ", "계좌정보 조회 retrofit");


        Call<AccountInfoRes> call = mypageAPI.getAccountInfo("Bearer " + sessionManager.fetchAuthToken());
        call.enqueue(new Callback<AccountInfoRes>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<AccountInfoRes> call, Response<AccountInfoRes> response) {
                if (response.isSuccessful()){

                    accountInfoRes = response.body();
                    // Log.d("AccountInfo Success ", String.valueOf(accountInfoRes.getAccountInfo().getUserType()));

                    virualBankNm.setText(accountInfoRes.getAccountInfo().getVirtualAcct().get(0).getBankName());
                    virualAcctNm.setText(accountInfoRes.getAccountInfo().getVirtualAcct().get(0).getCustomerName());
                    virualAcctNo.setText(accountInfoRes.getAccountInfo().getVirtualAcct().get(0).getAccountNumber());

                    bankNm.setText(accountInfoRes.getAccountInfo().getRealAcct().get(0).getBankName());
                    acctNm.setText(accountInfoRes.getAccountInfo().getRealAcct().get(0).getAccountName());
                    acctNo.setText(accountInfoRes.getAccountInfo().getRealAcct().get(0).getAccountNumber());

                    balance.setText("보유금액 : " + String.format("%,d", accountInfoRes.getAccountInfo().getBalance()) + " 원");
                }
            }

            @Override
            public void onFailure(Call<AccountInfoRes> call, Throwable t) {
                Log.d ("API",  "mypageAPI.getAccountInfo Failure ..." + t.getMessage());
                t.printStackTrace();
            }
        });

    }

    public void getSeyfertList(int inquiryTp, int inquiryYear, int inquiryMonth, String inquiryFromDt, String inquiryToDt) {
        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        MypageAPI mypageAPI = retrofit.create(MypageAPI.class);

//        inquiryTp = 1;
//        inquiryYear = 0;
//        inquiryMonth = 0;
//        inquiryFromDt = "";
//        inquiryToDt = "";
        SessionManager sessionManager = new SessionManager(getActivity());
        Log.d("getSeyfertList : TP = ", inquiryTp + " - " + inquiryFromDt + " - " + inquiryToDt);


        Call<SeyfertListRes> call = mypageAPI.getSeyfertList("Bearer " + sessionManager.fetchAuthToken(),
                                                                                Integer.toString(inquiryTp),
                                                                                Integer.toString(inquiryYear),
                                                                                Integer.toString(inquiryMonth), inquiryFromDt, inquiryToDt);

        call.enqueue(new Callback<SeyfertListRes>() {
            @Override
            public void onResponse(Call<SeyfertListRes> call, Response<SeyfertListRes> response) {
                if (response.isSuccessful()){

                    seyfertListRes = response.body();

                    Log.d("SeyfertList Success ", String.valueOf(seyfertListRes.getData().getTransactionList().size()));
                    mRecyclerView = view.findViewById(R.id.recyclerView);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerAdapter = new SeyfertListAdapter(getActivity(), seyfertListRes.getData().getTransactionList());
                    mRecyclerView.setAdapter(mRecyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<SeyfertListRes> call, Throwable t) {
                Log.d ("API",  "mypageAPI.getSeyfertList Failure ..." + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onInquiryButtonClicked(int jobTp, int year, int month, String fromDate, String toDate) {
        Log.d("SeyfertListFragment", "조회 button clicke : jobTp = " + jobTp + ", year = " + year + ", month = " + month + ", fromDate = " + fromDate + ", toDate = " + toDate);
        inquiryCond.setText(inquiryString(jobTp, year, month, fromDate, toDate));
        getSeyfertList(jobTp, year, month, fromDate, toDate);
    }

    @Override
    public void onCancelButtonClicked(String text) {
        Log.d("SeyfertListFragment", "취소 button clicke : " + text);
    }

    public String inquiryString(int jobTp, int year, int month, String fromDate, String toDate){

        String inquiryString="";

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate, endDate = dateFormat.format(currentDate);

        Calendar calendar = Calendar.getInstance();

        switch (jobTp) {
            case 1:
                endDate = dateFormat.format(currentDate);
                calendar.add(Calendar.MONTH, -1);
                startDate = dateFormat.format(calendar.getTime());
                return "1개월 (" + startDate + " ~ " + endDate + ")";
            case 2:
                endDate = dateFormat.format(currentDate);
                calendar.add(Calendar.MONTH, -3);
                startDate = dateFormat.format(calendar.getTime());
                return "3개월 (" + startDate + " ~ " + endDate + ")";
            case 3:
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month-1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                startDate = dateFormat.format(calendar.getTime());

                int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                calendar.set(Calendar.DAY_OF_MONTH, lastDay);
                endDate = dateFormat.format(calendar.getTime());

                return "월별 (" + startDate + " ~ " + endDate + ")";
            case 4:
                return "직접입력 (" + fromDate + " ~ " + toDate + ")";
            default:
                return "";
        }
    }
}
