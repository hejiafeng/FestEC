package com.diabin.latte.net.rx;

import android.content.Context;

import com.diabin.latte.net.HttpMethod;
import com.diabin.latte.net.RestClientBuilder;
import com.diabin.latte.net.RestCreator;
import com.diabin.latte.net.RestService;
import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.net.callback.RequestCallbacks;
import com.diabin.latte.net.download.DownloadHandler;
import com.diabin.latte.ui.LatteLoader;
import com.diabin.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2018/2/17.
 * 传入参数，无顺序要求，建造者模式
 */

public class RxRestClient {
    private final String URL;
//    private final Map<String,Object> PARAMS;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RxRestClient(String url,
                        Map<String, Object> params,
//                        IRequest request,
//                        String download_dir,
//                        String extension,
//                        String name,
//                        ISuccess success,
//                        IFailure failure,
//                        IError error,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
//        this.PARAMS = PARAMS;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
}
    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method){
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        if (LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method){
            case GET:
                observable = service.get(URL,PARAMS);
                break;
            case POST:
                observable = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL,BODY);
                break;
            case PUT:
                observable = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file",FILE.getName());
                observable = RestCreator.getRxRestService().upload(URL,body);
                break;
            default:
                break;
        }

//        if (call!=null){
//            call.enqueue(getRequestCallback());
//        }
        return observable;
    }

//    private Callback<String> getRequestCallback(){
//        return new RequestCallbacks(
//                REQUEST,
//                SUCCESS,
//                FAILURE,
//                ERROR,
//                LOADER_STYLE
//        );
//    }

    public final Observable<String> get(){
        return request(HttpMethod.GET);
    }

    public final Observable<String> post(){
        if (BODY == null){
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params muste be null!");
            }
        }
        return request(HttpMethod.POST_RAW);
    }

    public final Observable<String> put(){
        if (BODY == null){
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params muste be null!");
            }
        }
        return request(HttpMethod.PUT_RAW);
//        request(HttpMethod.PUT);
    }

    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download(){
//        request(HttpMethod);
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return  responseBodyObservable;
//        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handlerDownload();

    }


}
