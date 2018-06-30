package com.diabin.latte.net;

import android.util.Log;

import com.diabin.latte.app.ConfigKeys;
import com.diabin.latte.app.ConfigType;
import com.diabin.latte.app.Latte;
import com.diabin.latte.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/2/17.
 */

public class RestCreator {

    private static final class ParamsHolder{
        public  static final WeakHashMap<String,Object> PARAMS= new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE=
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE=
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }
    /**
     * service接口
     */
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder{
        private static final String BASE_URL= (String) Latte.getConfigurations().
                get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjava 加载进去
                .build();
    }
    private static final class OkHttpHolder{
        private static final int TIME_OUT=60;
        //后续改
        private static final OkHttpClient.Builder BULDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = (ArrayList<Interceptor>) Latte.getConfigurations().get(ConfigKeys.INTERCEPTOR);
        private static OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:INTERCEPTORS){
                    BULDER.addInterceptor(interceptor);
                }

            }
            return BULDER;
        }
//        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
//                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
//                .build();

        private static final OkHttpClient OK_HTTP_CLIENT=addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }




}
