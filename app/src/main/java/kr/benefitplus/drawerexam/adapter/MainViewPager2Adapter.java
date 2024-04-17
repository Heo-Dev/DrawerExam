package kr.benefitplus.drawerexam.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import kr.benefitplus.drawerexam.R;
import kr.benefitplus.drawerexam.fragment.ImageFragment;

public class MainViewPager2Adapter extends RecyclerView.Adapter<MainViewPager2Adapter.ViewHolder> {

    private static final String TAG = "MainViewPager2Adapter";

    private final Context context;
    private final String[] slideImage;

    public MainViewPager2Adapter(Context context, String[] slideImage) {
        this.context = context;
        this.slideImage = slideImage;
    }

    @NonNull
    @Override
    public MainViewPager2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_slide_itrem, parent, false);
        return new MainViewPager2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewPager2Adapter.ViewHolder holder, int position) {
        Log.d ("MainViewPager2Adapter", String.valueOf(position % slideImage.length));
        holder.bindSliderImage("Slider Text" + position % slideImage.length, slideImage[position % slideImage.length]);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE; // slideImage.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textId;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.text_id);
            imageView = itemView.findViewById(R.id.image_slide);
        }

        public void bindSliderImage(String text, String imageUrl){
            try{
                textId.setText(text);
                Glide.with(context).load(imageUrl).transform(new RoundedCorners(30)).into(imageView);
            }catch (Exception e){
                Log.d (TAG, "Glide Error : " + e.getMessage());
            }
        }
    }
}
