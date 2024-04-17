package kr.benefitplus.drawerexam.fragment;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import java.util.Calendar;

import kr.benefitplus.drawerexam.R;

public class DatePickerFragment extends DialogFragment {

    DateSelectedListener dateSelectedListener;

    private String dateTp;
    int year, month, day;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.date_picker, null);

        dateSelectedListener = (DatePickerFragment.DateSelectedListener) getParentFragment();

        Bundle args = getArguments();
        dateTp = args.getString("tp");
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");

        NumberPicker yearPicker = view.findViewById(R.id.year_picker);
        NumberPicker monthPicker = view.findViewById(R.id.month_picker);
        NumberPicker dayPicker = view.findViewById(R.id.day_picker);

        yearPicker.setMaxValue(2040);
        yearPicker.setMinValue(2000);
        yearPicker.setValue(year);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(month);

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        dayPicker.setValue(day);

        yearPicker.setWrapSelectorWheel(false);
        monthPicker.setWrapSelectorWheel(false);
        dayPicker.setWrapSelectorWheel(false);

        // 애니매이션 객체생성
        final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(yearPicker, "value", yearPicker.getValue());
        objectAnimator.setDuration(500);

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                objectAnimator.setIntValues(newVal);
                objectAnimator.start();

                year = newVal;
                dayPicker.setMaxValue(setFebDate());
            }
        });

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                month = newVal;
                dayPicker.setMaxValue(setFebDate());

            }
        });

        // 다이얼로그를 생성하고 설정합니다.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 확인 버튼을 클릭했을 때의 동작을 여기에 구현합니다.
                        dateSelectedListener.onSelectedDate(dateTp, yearPicker.getValue(), monthPicker.getValue(), dayPicker.getValue());
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 취소 버튼을 클릭했을 때의 동작을 여기에 구현합니다.
                    }
                });
        // AlertDialog 객체를 반환합니다.
        return builder.create();
    }

    public int setFebDate (){

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }else{
            // 4로 나눠 떨어지면 윤달,
            // 예외로 100으로 나눠 떨어지면 평달
            // 예외로 400으로 나눠 떨어지면 윤달
            return (year % 4 != 0)  ? 28 : ((year % 100 != 0) ? 29 : ((year % 400 != 0) ? 28 : 29));
        }
    }

    public interface DateSelectedListener {
        void onSelectedDate(String dateTp, int year, int month, int day);
    }
}
