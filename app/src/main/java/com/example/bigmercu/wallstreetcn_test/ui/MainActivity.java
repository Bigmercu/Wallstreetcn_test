package com.example.bigmercu.wallstreetcn_test.ui;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bigmercu.wallstreetcn_test.R;
import com.example.bigmercu.wallstreetcn_test.contract.GetDataContract;
import com.example.bigmercu.wallstreetcn_test.entity.Message;
import com.example.bigmercu.wallstreetcn_test.entity.Stock;
import com.example.bigmercu.wallstreetcn_test.model.GetDataModelImpl;
import com.example.bigmercu.wallstreetcn_test.presenter.GetDataPresenterImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.bigmercu.wallstreetcn_test.R.id.recyclerView;

public class MainActivity extends AppCompatActivity implements GetDataContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GetDataContract.Presenter mGetDataPresenter;
    private RecyclerView mRecyclerView ;
    private List<Message.MessagesBean> mMessage = new ArrayList<>();
    private  List<Stock> mStocks = new ArrayList<>();
    private RecyclerAdapter mRecyclerAdapter;
    private ArrayList<String> mSymbolCodeList = new ArrayList<>();
    private ArrayList<String> mFiledList = new ArrayList<>();
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetDataPresenterImpl(this);
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter = new RecyclerAdapter(getApplicationContext(),mMessage,mStocks);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        readCacheFromAssets();
    }

    private void readCacheFromAssets(){
        AssetManager assetManager = getAssets();
        mGetDataPresenter.getLocalData(assetManager);
    }

    private void initData(){
        geiSymbolList();
        mFiledList.add("prod_name");
        mFiledList.add("px_change");
        mFiledList.add("last_px");
        mFiledList.add("px_change_rate");
        mFiledList.add("trade_status");
    }

    private   List<String>  geiSymbolList(){
        JSONArray jsonObj = null;
        try {
            String json = GetDataModelImpl.getJsonData(getApplicationContext().getAssets());
            jsonObj = new JSONObject(json).getJSONArray("Messages");
            for(int i =0;i < jsonObj.length();i++){
                JSONArray jsonArray = new JSONObject(String.valueOf(jsonObj.get(i))).getJSONArray("Stocks");
                for(int j = 0;j < jsonArray.length();j++) {
                    String Symbol = new JSONObject(String.valueOf(jsonArray.get(j))).getString("Symbol");
                    if(Symbol != "" && Symbol != null){
                        mSymbolCodeList.add(Symbol);
                    }
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mSymbolCodeList;
    }

    @Override
    public void setData(Message message) {
        mMessage.clear();
        mMessage.addAll(message.getMessages());
        mRecyclerAdapter.notifyDataSetChanged();
        mGetDataPresenter.getRemoteData(mSymbolCodeList,mFiledList);
    }

    @Override
    public void setData(List<Stock> stock) {
        mStocks.clear();
        mStocks.addAll(stock);
        mRecyclerAdapter.notifyDataSetChanged();
        mRecyclerAdapter.notifyMap();
        mCount++;
        Snackbar.make(mRecyclerView,"第" + mCount + "次更新",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFiled(String info) {
        Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(GetDataContract.Presenter presenter) {
        this.mGetDataPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGetDataPresenter.onCancle();
    }
}
