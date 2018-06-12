package com.diabin.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.diabin.latte.app.Latte;

/**
 * Created by Administrator on 2018/4/22.
 */

public class DimenUtil {

    public static int getScreenWith(){
//        ExampleApp
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHight(){
//        ExampleApp
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
