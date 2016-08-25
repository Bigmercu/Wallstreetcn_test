package com.example.bigmercu.wallstreetcn_test.presenter;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.bigmercu.wallstreetcn_test.contract.GetDataContract;
import com.example.bigmercu.wallstreetcn_test.entity.Message;
import com.example.bigmercu.wallstreetcn_test.entity.Stock;
import com.example.bigmercu.wallstreetcn_test.model.GetDataModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigmercu on 2016/8/22.
 * Email: bigmercu@gmail.com
 */

public class GetDataPresenterImpl implements GetDataContract.Presenter,GetDataModelImpl.onGetDataListener{
    private GetDataContract.View mView;
    private GetDataModelImpl mGetDataModel;
    private static final String TAG = GetDataPresenterImpl.class.getSimpleName();

    public GetDataPresenterImpl(GetDataContract.View view){
        this.mView = view;
        mGetDataModel = GetDataModelImpl.getInstance();
        view.setPresenter(this);
    }

    @Override
    public void getRemoteData( ArrayList<String> symbolCodeList,  ArrayList<String> filedList) {
        mGetDataModel.getRemoteData(symbolCodeList,filedList,GetDataPresenterImpl.this);
    }

    @Override
    public void getLocalData(AssetManager asserter) {
        mGetDataModel.getLocalData(asserter,this);
    }

    @Override
    public void onCancle() {
        mGetDataModel.onCancle();
    }

    @Override
    public void start() {
        Log.d(TAG,"start");
    }

    @Override
    public void onSuccess(Message message) {
        mView.setData(message);
    }

    @Override
    public void onSuccess( List<Stock> stock) {
        mView.setData(stock);
    }

    @Override
    public void onFiled(String info) {
        mView.onFiled(info);
    }
}
