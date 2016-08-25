package com.example.bigmercu.wallstreetcn_test.ui;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bigmercu.wallstreetcn_test.R;
import com.example.bigmercu.wallstreetcn_test.entity.Message;
import com.example.bigmercu.wallstreetcn_test.entity.Stock;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bigmercu on 2016/8/22.
 * Email: bigmercu@gmail.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<Message.MessagesBean> mMessage;
    private List<Stock> mStocks;
    private Map<String,String> mSymbolMap = new HashMap<>();

    public RecyclerAdapter(Context context, List<Message.MessagesBean> message, List<Stock> stocks) {
        this.mContext = context;
        this.mMessage = message;
        this.mStocks = stocks;
    }
    public void notifyMap(){
        mSymbolMap.clear();
        for(int i = 0;i< mStocks.size();i++){
            mSymbolMap.put(mStocks.get(i).symbol, String.valueOf(i));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mLinearLayout1.removeAllViews();
        holder.mGoodCount.setText(mMessage.get(position).getLikeCount());
        holder.mLongText.setText(mMessage.get(position).getTitle());
        holder.mFrom.setText(mMessage.get(position).getSource());
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String time = hour + ":" + minute;
        holder.mTime.setText(time);
        holder.setData(position, mMessage,mContext,mStocks,mSymbolMap);
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mLongText;
        private TextView mTime;
        private TextView mFrom;
        private TextView mGoodCount;
        private LinearLayout mLinearLayout1;

        public void setData(int position,List<Message.MessagesBean> mMessage,Context mContext,List<Stock> stocks,Map<String ,String> mSymbolMap){
            for (int i = 0; i < (mMessage.get(position).getStocks().size()>3?3:mMessage.get(position)
                    .getStocks().size()); i++) {
                LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
                View view = mLayoutInflater.inflate(R.layout.symbol_view, null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                view.setLayoutParams(layoutParams);
                mLinearLayout1.addView(view);
                TextView mTextView = (TextView) view.findViewById(R.id.gold1);
                ImageView mImageView = (ImageView) view.findViewById(R.id.img1);
                if(mSymbolMap.size()>0) {
                    Double mTemp = Double.parseDouble(stocks.get(Integer.parseInt(mSymbolMap
                            .get(mMessage.get(position).getStocks().get(i).getSymbol()))).px_change_rate);
                    if(mTemp>0){
                        mTextView.setText(stocks.get(Integer.parseInt(mSymbolMap.get(mMessage
                                .get(position).getStocks().get(i).getSymbol()))).name + String .format("%+.2f",mTemp));
                        mTextView.setTextColor(mContext.getResources().getColor(R.color.stock_up));
                        mImageView.setImageResource(R.mipmap.ic_stock_up);
                    }else {
                        mTextView.setText(stocks.get(Integer.parseInt(mSymbolMap.get(mMessage
                                .get(position).getStocks().get(i).getSymbol()))).name + String .format("%.2f",mTemp));
                        mTextView.setTextColor(mContext.getResources().getColor(R.color.stock_down));
                        mImageView.setImageResource(R.mipmap.ic_stock_down);
                    }
                }else {
                    mTextView.setText(mMessage.get(position).getStocks().get(i).getName());
                }
            }
        }


        public MyViewHolder(View itemView) {
            super(itemView);
            mLongText = (TextView) itemView.findViewById(R.id.longText);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mFrom = (TextView) itemView.findViewById(R.id.from);
            mGoodCount = (TextView) itemView.findViewById(R.id.likeCount);
            mLinearLayout1 = (LinearLayout) itemView.findViewById(R.id.linear);
        }

    }
}