package com.diabin.lattteec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diabin.latte.app.AccountManager;
import com.diabin.lattteec.database.DatabaseManager;
import com.diabin.lattteec.database.UserProfile;


public class SignHandler {

    public static void onSignIn(String response,ISignListener mISignListener){
        //插入数据
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        mISignListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener mISignListener){
        //插入数据
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        mISignListener.onSignUpSuccess();

    }
}
