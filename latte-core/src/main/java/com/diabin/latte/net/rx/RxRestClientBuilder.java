package com.diabin.latte.net.rx;

import android.content.Context;

import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.RestCreator;
import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/2/19.
 */

public class RxRestClientBuilder {
    private  String mUrl = null;
//    private  Map<String,Object> mParams;
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
//    private IRequest mIRequst = null;
//    private ISuccess mISuccess = null;
//    private IFailure mIFailure = null;
//    private IError mIError = null;
    private  RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mfile = null;
//    private  String DOWNLOAD_DIR = null;
//    private  String EXTENSION = null;
//    private  String NAME = null;
    RxRestClientBuilder(){
    }

    public final RxRestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
//        this.mParams=params;
        PARAMS.putAll(params);
        return this;
    }
    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mfile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mfile = new File(file);
        return this;
    }




    public final RxRestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
//    public final RxRestClientBuilder onRequest(IRequest iRequest){
//        this.mIRequst=iRequest;
//        return this;
//    }
//    public final RxRestClientBuilder success(ISuccess iSuccess){
//        this.mISuccess=iSuccess;
//        return this;
//    }
//    public final RxRestClientBuilder failure(IFailure iFailure){
//        this.mIFailure=iFailure;
//        return this;
//    }
//
//    public final RxRestClientBuilder download_dir(String DOWNLOAD_DIR){
//        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
//        return this;
//    }
//
//
//    public final RxRestClientBuilder extension(String EXTENSION){
//        this.EXTENSION = EXTENSION;
//        return this;
//    }
//
//    public final RxRestClientBuilder name(String NAME){
//        this.NAME= NAME;
//        return this;
//    }
//
//    public final RxRestClientBuilder error(IError iError){
//        this.mIError=iError;
//        return this;
//    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

//    private Map<String,Object> checkParams(){
//        if (mParams==null){
//            return new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RxRestClient build(){
        return  new RxRestClient(mUrl,PARAMS,
//                mIRequst,DOWNLOAD_DIR,
//                EXTENSION,NAME,mISuccess,
//                mIFailure,mIError,
                mBody,mfile,
                mContext,mLoaderStyle);
    }



}
