package kr.benefitplus.drawerexam.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import kr.benefitplus.drawerexam.DTO.SeyfertListRes;
import kr.benefitplus.drawerexam.R;

public class SeyfertListSubAdapter extends RecyclerView.Adapter<SeyfertListSubAdapter.SubViewHolder> {

    private final Context context;
    private final List<SeyfertListRes.Transaction> trans;
    public SeyfertListSubAdapter(Context context, List<SeyfertListRes.Transaction> value){

        this.context = context;
        this.trans = value;
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seyfertlist_subitem, parent, false);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        SeyfertListRes.Transaction transaction = trans.get(position);
        holder.onBind(transaction);
    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public class SubViewHolder extends RecyclerView.ViewHolder {

        TextView tradTime;
        TextView rmrk;
        TextView tradTp;
        TextView title;
        TextView tradAmt;
        TextView balance;
        DecimalFormat decFormat = new DecimalFormat("###,###");


        public SubViewHolder(@NonNull View itemView) {
            super(itemView);

            tradTime = itemView.findViewById(R.id.tradTime);
            rmrk = itemView.findViewById(R.id.rmrk);
            tradTp = itemView.findViewById(R.id.tradTp);
            title = itemView.findViewById(R.id.title);
            tradAmt = itemView.findViewById(R.id.tradAmt);
            balance = itemView.findViewById(R.id.balance);
        }

        public void onBind(SeyfertListRes.Transaction transaction) {

            tradTime.setText(transaction.getTransactionTime());
            rmrk.setText(transaction.getRemark());
            tradTp.setText(transaction.getTransactionType());
            title.setText(transaction.getTitle());
            if (transaction.getTransactionType().equals("입금")){
                tradTp.setTextColor(Color.parseColor("#d63031"));
                tradAmt.setTextColor(Color.parseColor("#d63031"));
            }else{
                tradTp.setTextColor(Color.parseColor("#0984e3"));
                tradAmt.setTextColor(Color.parseColor("#0984e3"));
            }
            tradAmt.setText(decFormat.format(transaction.getAmount()) + "원");
            balance.setText("잔액 : " + decFormat.format(transaction.getBalance()) + "원");

        }
    }
}
