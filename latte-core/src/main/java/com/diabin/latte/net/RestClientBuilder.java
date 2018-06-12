package com.diabin.latte.net;

import android.content.Context;

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

public class RestClientBuilder {
    private  String mUrl = null;
//    private  Map<String,Object> mParams;
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequst = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private  RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mfile = null;
    private  String DOWNLOAD_DIR = null;
    private  String EXTENSION = null;
    private  String NAME = null;
    RestClientBuilder(){
    }

    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder params(WeakHashMap<String,Object> params){
//        this.mParams=params;
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mfile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mfile = new File(file);
        return this;
    }




    public final RestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequst=iRequest;
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }

    public final RestClientBuilder download_dir(String DOWNLOAD_DIR){
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        return this;
    }


    public final RestClientBuilder extension(String EXTENSION){
        this.EXTENSION = EXTENSION;
        return this;
    }

    public final RestClientBuilder name(String NAME){
        this.NAME= NAME;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError=iError;
        return this;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
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

    public final RestClient build(){
        return  new RestClient(mUrl,PARAMS,mIRequst,DOWNLOAD_DIR,EXTENSION,NAME,mISuccess,mIFailure,mIError,mBody,mfile,mContext,mLoaderStyle);
    }



}
