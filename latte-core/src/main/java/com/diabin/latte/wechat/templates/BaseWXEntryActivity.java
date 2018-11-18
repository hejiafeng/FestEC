package com.diabin.latte.wechat.templates;

import com.diabin.latte.wechat.BaseWXActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;

public abstract class BaseWXEntryActivity extends BaseWXActivity{
    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);

}
