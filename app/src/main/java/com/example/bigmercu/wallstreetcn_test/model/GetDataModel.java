package com.example.bigmercu.wallstreetcn_test.model;

import android.content.res.AssetManager;

import com.example.bigmercu.wallstreetcn_test.entity.Message;
import com.example.bigmercu.wallstreetcn_test.entity.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigmercu on 2016/8/22.
 * Email: bigmercu@gmail.com
 */

public interface GetDataModel {
    void getRemoteData(ArrayList<String> symbolCodeList, ArrayList<String> filedList, onGetDataListener listener);

    void getLocalData(AssetManager asserter, onGetDataListener listener);

    void onCancle();
    interface onGetDataListener{
        void onSuccess(Message message);
        void onSuccess(List<Stock> stock);
        void onFiled(String info);
    }
}
