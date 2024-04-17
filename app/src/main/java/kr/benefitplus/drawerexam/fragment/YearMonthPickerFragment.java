package kr.benefitplus.drawerexam.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import kr.benefitplus.drawerexam.R;

public class YearMonthPickerFragment extends DialogFragment {

    YearMonthSelectedListener yearMonthSelectedListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        yearMonthSelectedListener = (YearMonthPickerFragment.YearMonthSelectedListener)getParentFragment();

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.year_month_picker, null);

        Bundle args = getArguments();
        int year = args.getInt("year");
        int month = args.getInt("month");

        NumberPicker yearPicker = view.findViewById(R.id.year_picker);
        NumberPicker monthPicker = view.findViewById(R.id.month_picker);

        yearPicker.setMaxValue(2040);
        yearPicker.setMinValue(2020);
        yearPicker.setValue(year);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(month);

        //  순환 안되게 막기
        yearPicker.setWrapSelectorWheel(false);
        monthPicker.setWrapSelectorWheel(false);

        // 다이얼로그를 생성하고 설정합니다.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
//                .setTitle("제목")
//                .setMessage("다이얼로그 메시지")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 확인 버튼을 클릭했을 때의 동작을 여기에 구현합니다.
                        int year = yearPicker.getValue();
                        int month = monthPicker.getValue();
                        yearMonthSelectedListener.onSelectedYearMonth(year, month);
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

    public interface YearMonthSelectedListener {
        void onSelectedYearMonth(int year, int month);
    }
}
