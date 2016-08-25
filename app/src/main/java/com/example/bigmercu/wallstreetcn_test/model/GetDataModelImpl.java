package com.example.bigmercu.wallstreetcn_test.model;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.bigmercu.wallstreetcn_test.entity.Message;
import com.example.bigmercu.wallstreetcn_test.entity.Stock;
import com.example.bigmercu.wallstreetcn_test.util.HSJsonUtil;
import com.example.bigmercu.wallstreetcn_test.util.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bigmercu on 2016/8/22.
 * Email: bigmercu@gmail.com
 */

public class GetDataModelImpl implements GetDataModel {
    private static final String TAG = GetDataModelImpl.class.getSimpleName();

    private RemoteDataService mRemoteDataService;
    private Subscription mSubscription;
    private Subscription mSubscription1;

    public GetDataModelImpl() {
        mRemoteDataService = RetrofitClient.getInstance().create(RemoteDataService.class);
    }

    public static class SingleHolder {
        private static final GetDataModelImpl INSTANCE = new GetDataModelImpl();
    }

    public static GetDataModelImpl getInstance() {
        return SingleHolder.INSTANCE;
    }


    @Override
    public void getRemoteData(ArrayList<String> symbolCodeList, ArrayList<String> filedList, final onGetDataListener listener) {
        Log.d(TAG, "getRemoteData");
        String symbolCode = "";
        String fileds = "";

        for (String tmp : symbolCodeList) {
            symbolCode += tmp += ",";
        }
        for (String tmp : filedList) {
            fileds += tmp += ",";
        }

        symbolCode = symbolCode.substring(0, symbolCode.length() - 1);
        fileds = fileds.substring(0, fileds.length() - 1);

        final String finalSymbolCode = symbolCode;
        final String finalFileds = fileds;

        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                mSubscription1 = mRemoteDataService.getRemoteData(finalSymbolCode, finalFileds)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ResponseBody>() {
                            @Override
                            public void call(ResponseBody remoteBean) {
                                List<Stock> stock = new ArrayList<Stock>();
                                try {
                                    stock = HSJsonUtil.getRealStockList(remoteBean.string(), "snapshot");
                                    for(Stock temp:stock){
                                        Log.d(TAG,temp.symbol + ":" + temp.px_change_rate);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                listener.onSuccess(stock);
                            }
                        });
            }
        };
        subscriber.onNext(1);
        mSubscription = Observable.interval(2, 2, TimeUnit.SECONDS)
                .subscribe(subscriber);
    }

    @Override
    public void getLocalData(AssetManager asserter, onGetDataListener listener) {
        try {
            Message message = new Gson().fromJson(getJsonData(asserter), Message.class);
            asserter = null;
            if (message != null) {
                listener.onSuccess(message);
            } else {
                listener.onFiled("no data");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJsonData(AssetManager asserter) {
        InputStream inputStream = null;
        try {
            inputStream = asserter.open("data.json");
            int size = inputStream.available();
            int len = -1;
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            String data = new String(bytes);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public void onCancle() {
        if(!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        if(!mSubscription1.isUnsubscribed()){
            mSubscription1.unsubscribe();
        }
    }
}
