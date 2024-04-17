package kr.benefitplus.drawerexam.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Locale;

import kr.benefitplus.drawerexam.R;

public class InquiryCondBottomSheetFragment extends BottomSheetDialogFragment
                                            implements DatePickerFragment.DateSelectedListener, YearMonthPickerFragment.YearMonthSelectedListener {

    private LinearLayout inquiryYearMonthLayout, inquiryFromToLayout;
    private BottomSheetListener bottomSheetListener;
    private TextView inquiryTp1, inquiryTp2, inquiryTp3, inquiryTp4;

    private int jobTp = 1;
    private ImageView yearMonthPicker, fromDatePicker, toDatePicker;
    private TextView yearMonth, fromDate, toDate;

    private int year, month, day;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.inquiry_cond_bottom_sheet, viewGroup, false);
        bottomSheetListener = (BottomSheetListener)getParentFragment();

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);


        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetListener.onCancelButtonClicked("onCancelButton - Clicked");
                dismiss();
            }
        });

        inquiryTp1 = view.findViewById(R.id.inquiry_tp1);
        inquiryTp2 = view.findViewById(R.id.inquiry_tp2);
        inquiryTp3 = view.findViewById(R.id.inquiry_tp3);
        inquiryTp4 = view.findViewById(R.id.inquiry_tp4);

        inquiryYearMonthLayout = view.findViewById(R.id.inquiry_year_month_layout);
        inquiryFromToLayout = view.findViewById(R.id.inquiry_from_to_layout);
        yearMonth = view.findViewById(R.id.year_month);
        yearMonth.setText(year + "년 " + (month < 10 ? "0" + month : month) + "월");

        fromDate = view.findViewById(R.id.from_date);
        fromDate.setText(year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day));
        toDate = view.findViewById(R.id.to_date);
        toDate.setText(year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day));

        yearMonthPicker = view.findViewById(R.id.year_month_picker);
        fromDatePicker = view.findViewById(R.id.from_date_picker);
        toDatePicker = view.findViewById(R.id.to_date_picker);


        Button inquiryButton = view.findViewById(R.id.inquiry_button);
        inquiryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (jobTp == 1){
                    bottomSheetListener.onInquiryButtonClicked(jobTp, 0, 0, "", "");
                }else if (jobTp == 2){
                    bottomSheetListener.onInquiryButtonClicked(jobTp, 0, 0, "", "");
                }else if (jobTp == 3){
                    bottomSheetListener.onInquiryButtonClicked(jobTp, year, month, "", "");
                }else if (jobTp == 4){
                    String fromDateStr = null, toDateStr = null;
                    fromDateStr = fromDate.getText().toString();
                    toDateStr = toDate.getText().toString();
                    bottomSheetListener.onInquiryButtonClicked(jobTp, 0, 0, fromDateStr, toDateStr);
                }
                dismiss();
            }
        });


        inquiryTpSelect(jobTp);

        inquiryTp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobTp = 1;
                inquiryTpSelect(jobTp);
            }
        });
        inquiryTp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobTp = 2;
                inquiryTpSelect(jobTp);
            }
        });
        inquiryTp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobTp = 3;
                inquiryTpSelect(jobTp);
            }
        });
        inquiryTp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobTp = 4;
                inquiryTpSelect(jobTp);
            }
        });

        yearMonthPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle args = new Bundle();
                args.putInt("year", year);
                args.putInt("month", month);

                YearMonthPickerFragment yearMonthPickerFragment = new YearMonthPickerFragment();
                yearMonthPickerFragment.setArguments(args);
                yearMonthPickerFragment.show(getChildFragmentManager(), "datePicker");

            }
        });

        fromDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("tp", "fromDate");
                args.putInt("year", year);
                args.putInt("month", month);
                args.putInt("day", day);

                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setArguments(args);
                datePickerFragment.show(getChildFragmentManager(), "datePicker");
            }
        });

        toDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("tp", "toDate");
                args.putInt("year", year);
                args.putInt("month", month);
                args.putInt("day", day);

                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setArguments(args);
                datePickerFragment.show(getChildFragmentManager(), "datePicker");
            }
        });

        return view;
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }

    @Override
    public void onSelectedDate(String tp, int y, int m, int d) {

        if (tp == "fromDate"){
            fromDate.setText(y+"-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d));
            // toDate.setText(year+"/" + (month < 10 ? "0" + month : month) + "/" + (day < 10 ? "0" + day : day));
        }else{
            toDate.setText(y+"-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d));
        }
    }

    @Override
    public void onSelectedYearMonth(int y, int m) {
        year = y;
        month = m;
        yearMonth.setText(year + "년 " + (month < 10 ? "0" + month : month) + "월");
    }


    public interface BottomSheetListener {
        void onInquiryButtonClicked(int jobTp, int year, int month, String fromDate, String toDate);
        void onCancelButtonClicked(String text);
    }

    public void inquiryTpSelect(int tp){

        inquiryTp1.setBackgroundResource(R.drawable.border);
        inquiryTp2.setBackgroundResource(R.drawable.border);
        inquiryTp3.setBackgroundResource(R.drawable.border);
        inquiryTp4.setBackgroundResource(R.drawable.border);

        switch (tp) {
            case 1:
                inquiryTp1.setBackgroundResource(R.drawable.bplus_button_yellow);
                inquiryYearMonthLayout.setVisibility(View.GONE);
                inquiryFromToLayout.setVisibility(View.GONE);
                break;
            case 2:
                inquiryTp2.setBackgroundResource(R.drawable.bplus_button_yellow);
                inquiryYearMonthLayout.setVisibility(View.GONE);
                inquiryFromToLayout.setVisibility(View.GONE);
                break;
            case 3:
                inquiryTp3.setBackgroundResource(R.drawable.bplus_button_yellow);
                inquiryYearMonthLayout.setVisibility(View.VISIBLE);
                inquiryFromToLayout.setVisibility(View.GONE);
                break;
            case 4:
                inquiryTp4.setBackgroundResource(R.drawable.bplus_button_yellow);
                inquiryYearMonthLayout.setVisibility(View.GONE);
                inquiryFromToLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
}
