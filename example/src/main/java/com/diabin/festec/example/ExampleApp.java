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
import com.diabin.latte.net.interceptors.DebugInterceptor;
import com.diabin.lattteec.database.DatabaseManager;
import com.diabin.lattteec.icon.FontEcModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

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
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
//        Stetho.initializeWithDefaults(this);

    }

    public static Context getContext() {
        return context;
    }
    private void initStetho(){
//        Steth
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }


}
