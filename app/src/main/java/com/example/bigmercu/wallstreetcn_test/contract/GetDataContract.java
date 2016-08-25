package com.example.bigmercu.wallstreetcn_test.contract;

import android.content.res.AssetManager;

import com.example.bigmercu.wallstreetcn_test.base.BasePresenter;
import com.example.bigmercu.wallstreetcn_test.base.BaseView;
import com.example.bigmercu.wallstreetcn_test.entity.Message;
import com.example.bigmercu.wallstreetcn_test.entity.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigmercu on 2016/8/22.
 * Email: bigmercu@gmail.com
 */

public interface GetDataContract {
    interface Presenter extends BasePresenter {
        void getRemoteData(ArrayList<String> symbolCodeList, ArrayList<String> filedList);

        void getLocalData(AssetManager asserter);
        void onCancle();
    }

    interface View extends BaseView<Presenter> {
        void setData(Message message);
        void setData(List<Stock> stock);
        void onFiled(String info);
    }
}
