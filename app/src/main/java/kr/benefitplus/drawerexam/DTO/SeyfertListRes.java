package kr.benefitplus.drawerexam.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class SeyfertListRes {
    @SerializedName("result")
    private boolean result;

    @SerializedName("result_msg")
    private String resultMsg;

    @SerializedName("data")
    private TransactionData data;

    // Getters and setters
    public boolean isResult() {
        return result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public TransactionData getData() {
        return data;
    }

    public class TransactionData {
        @SerializedName("trad_list")
        private Map<String, List<Transaction>> transactionList;

        // Getters and setters
        public Map<String, List<Transaction>> getTransactionList() {
            return transactionList;
        }
    }

    public class Transaction {
        @SerializedName("tid")
        private String transactionId;

        @SerializedName("trad_date")
        private String transactionDate;

        @SerializedName("trad_time")
        private String transactionTime;

        @SerializedName("trad_tp")
        private String transactionType;

        @SerializedName("rmrk")
        private String remark;

        @SerializedName("title")
        private String title;

        @SerializedName("round")
        private Integer round;

        @SerializedName("trad_amt")
        private int amount;

        @SerializedName("balance")
        private int balance;

        // Getters and setters

        public String getTransactionId() {
            return transactionId;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public String getTransactionTime() {
            return transactionTime;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public String getRemark() {
            return remark;
        }

        public String getTitle() {
            return title;
        }

        public Integer getRound() {
            return round;
        }

        public int getAmount() {
            return amount;
        }

        public int getBalance() {
            return balance;
        }
    }
}
