package com.example.bigmercu.wallstreetcn_test.model;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bigmercu on 2016/8/22.
 * Email: bigmercu@gmail.com
 */

public interface RemoteDataService {
    @GET("real")
    Observable<ResponseBody> getRemoteData(@Query("en_prod_code") String symbolCode, @Query("fields") String fileds);
}
