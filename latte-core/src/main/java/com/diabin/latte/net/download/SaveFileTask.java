package com.diabin.latte.net.download;


import android.os.AsyncTask;

import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;

import java.io.File;

import okhttp3.RequestBody;

public class SaveFileTask extends AsyncTask<Object,Void,File>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDor = (String) objects[0];
        String extension = (String) objects[1];
        final RequestBody body = (RequestBody) objects[2];

        return null;
    }
}
