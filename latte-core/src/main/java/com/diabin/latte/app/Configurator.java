package com.diabin.latte.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
* 项目名： FestEC
* 包名： com.diabin.latte.app
* 文件名： Configurator
* 创建者： HJF
* 创建时间： 2018/1/2221:06
* 描述： com.diabin.latte.app
 * 新建private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
 * 初始化是name为false
 * 优雅的单例模式
 * 取得全局成员变量方法 return LATTE_CONFIGS
 * 让成员变量 LATTE_CONFIGS 赋值方法
 * checkConfiguration():检查是否赋值给某个值，如果为false，就返回
 * 举一反三：WeakHashMap与室内机项目区别：
 * static修饰成员变量与方法
 * final修饰成员变量与方法
 *
 * 此类作用：Configurator 新建LATTE_CONFIGS,存储key，values。
 *
*/

public class Configurator {
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
//    private static final ArrayList<IconF>
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    //初始化，但是为false
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);

    }

    /**
     * 优雅的单例模式
     * 静态内部类
     * 线程安全的懒汉模式
     */
    public static Configurator getInstance(){
        return  Holder.INSTANCE;
    }
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }

    /**
     * 为什么不用static呢？
     * 还有不用public 为毛只用了？
     * @return
     */
    final  WeakHashMap<Object,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 为什么要转型呢？
     * 检查是否
     */
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 好好琢磨下这里是怎样写的？
     * @param key
     * @param <T>
     * @return
     */
//    @SuppressWarnings("unchecked")
//    final <T> T getConfiguration(Enum<ConfigType> key){
//        checkConfiguration();
//        return (T) LATTE_CONFIGS.get(key.name());
//    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId){
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET,appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity){
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY,activity);
        return this;
    }

}
