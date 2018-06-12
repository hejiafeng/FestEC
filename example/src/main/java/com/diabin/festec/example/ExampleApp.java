package com.diabin.festec.example;
/*
* 项目名： FestEC
* 包名： com.diabin.festec
* 文件名： ExampleApp
* 创建者： HJF
* 创建时间： 2018/1/1123:38
* 描述： com.diabin.festec
*/

import android.app.Application;
import android.content.Context;

import com.diabin.latte.app.Latte;

public class ExampleApp extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        /**
         * 1.先装在Latte中新建方法返回Configurator 然后再调用withApiHost和configure方法。
         * 2.一行代码就可以解决了。
         */


        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();

    }

    public static Context getContext() {
        return context;
    }


}
