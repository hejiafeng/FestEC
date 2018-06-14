package com.diabin.latte.app;

import android.app.Application;
import android.content.Context;

import java.util.WeakHashMap;

/**
* 项目名： FestEC
* 包名： com.diabin.latte.app
* 文件名： Latte
* 创建者： HJF
* 创建时间： 2018/1/1123:37
* 描述： com.diabin.latte.app
 * 此类作用：在init中初始化并赋值给.name() 返回Configurator对象
*/

public final class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();

    }
    public static WeakHashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Application getApplication(){
        return (Application) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());

    }
    public static Application getApplicationContext(){
        return (Application) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

//    public static Context getApplicationContext() {
//        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
//    }
//
//    public static <T> T getConfiguration(Object key) {
//        return getConfigurator().getConfiguration(key);
//    }
//
//    public static Configurator getConfigurator() {
//        return Configurator.getInstance();
//    }
}
