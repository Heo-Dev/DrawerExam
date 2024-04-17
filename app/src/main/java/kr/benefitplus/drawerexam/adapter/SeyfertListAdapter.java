package kr.benefitplus.drawerexam.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.benefitplus.drawerexam.DTO.SeyfertListRes;
import kr.benefitplus.drawerexam.R;

public class SeyfertListAdapter extends RecyclerView.Adapter<SeyfertListAdapter.ViewHolder>{

    private final Context context;
    private final Map<String, List<SeyfertListRes.Transaction>> transactionMap;

    public SeyfertListAdapter(Context context, Map<String, List<SeyfertListRes.Transaction>> transactionMap) {
        this.context = context;
        this.transactionMap = transactionMap;
    }


    @NonNull
    @Override
    public SeyfertListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seyfertlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Map.Entry<String, List<SeyfertListRes.Transaction>>> entryList = new ArrayList<>(transactionMap.entrySet());
        Map.Entry<String, List<SeyfertListRes.Transaction>> nthEntry = entryList.get(position);

        holder.onBind(nthEntry.getKey());
        holder.subRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        SeyfertListSubAdapter mRecyclerSubAdapter = new SeyfertListSubAdapter(context, nthEntry.getValue());
        holder.subRecyclerView.setAdapter(mRecyclerSubAdapter);
    }

    @Override
    public int getItemCount() {
        return transactionMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tradDate;
        RecyclerView subRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tradDate = itemView.findViewById(R.id.tead_date);
            subRecyclerView = itemView.findViewById(R.id.subRecyclerView);
        }

        public void onBind(String key) {
            tradDate.setText(key);
            Log.d("adapter ", key);
        }
    }
}
