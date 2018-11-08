package com.diabin.latte.app;

import com.diabin.latte.util.storage.LattePreference;

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户登录状态，登录后调用 储存使用sharePreference
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    //判断是否登录
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }


}
