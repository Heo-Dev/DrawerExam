package kr.benefitplus.drawerexam.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;

import kr.benefitplus.drawerexam.MainActivity;
import kr.benefitplus.drawerexam.R;
import kr.benefitplus.drawerexam.adapter.MainViewPager2Adapter;

public class MainFragment  extends Fragment {

    private final Handler headerHandler = new Handler();
    ViewPager2 viewPager2;
    MainViewPager2Adapter adapter;
    String[] imageUrls = new String[] {
            "https://benefitplus.kr/storage/main-slider/mx2g8bdQqgM9gBXVTidModHnLRPbWqfJxTDM6r5R.png",
            "https://benefitplus.kr/storage/main-slider/npPxqh572aGcceEwjClSUsKiwI45LrqDNvlytRyD.png",
            "https://benefitplus.kr/storage/main-slider/eqNEb7xXg5RSUqSzgzbX6tp2QnlMf9LtZa7UEaSz.png"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main, container, false);



        viewPager2 = view.findViewById(R.id.main_view_pager);
        adapter = new MainViewPager2Adapter(getContext(), imageUrls);

        // ViewPager를 위한 Transformer
        CompositePageTransformer transformer = new CompositePageTransformer();

        // Add margin between page
        transformer.addTransformer(new MarginPageTransformer(50));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                page.setScaleY(0.85f + v * 0.2f);
            }
        });

        // set clip padding
        viewPager2.setClipToPadding(false);

        // set clip children
        viewPager2.setClipChildren(false);

        // set page limit
        viewPager2.setOffscreenPageLimit(3);

        // set default start position
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        viewPager2.setAdapter(adapter);
        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                headerHandler.removeCallbacks(headerRunnable);
                headerHandler.postDelayed(headerRunnable, 5000);
            }
        });

        return view;
    }


    private final Runnable headerRunnable = new Runnable() {
        @Override public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1, true);
        }
    };

    @Override public void onPause() {
        super.onPause();
        headerHandler.removeCallbacks(headerRunnable);
    }

    @Override public void onResume() {
        super.onResume();
        headerHandler.postDelayed(headerRunnable, 3000);
    }
}
