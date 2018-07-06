package com.diabin.lattteec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ui.launcher.ScrollLauncherTag;
import com.diabin.latte.util.storage.LattePreference;
import com.diabin.latte.util.timer.BaseTimerTask;
import com.diabin.latte.util.timer.ITimerListener;
import com.diabin.lattteec.R;
import com.diabin.lattteec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LancherDelegate extends LatteDelegate implements ITimerListener{
    private static final String TAG = LancherDelegate.class.getSimpleName();
    @BindView(R2.id.tv_lanucher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;

    private int mCount = 5;

    @OnClick(R2.id.tv_lanucher_timer)
    void onClickTimerView(){
        if (mTimer!=null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }
    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();

    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LANUCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登录了App

        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
