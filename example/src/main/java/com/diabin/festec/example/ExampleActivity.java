package com.diabin.festec.example;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.diabin.latte.activities.ProxyActivity;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ui.launcher.ILauncherListener;
import com.diabin.latte.ui.launcher.OnLauncherFinishTag;
import com.diabin.lattteec.launcher.LancherDelegate;
import com.diabin.lattteec.launcher.LauncherScrollDelegate;
import com.diabin.lattteec.sign.ISignListener;
import com.diabin.lattteec.sign.SignInDelegate;
import com.diabin.lattteec.sign.SignUpDelegate;

/**
 * Created by Administrator on 2018/2/16.
 * 1,先写ProxyActivity,proxyActivity在latte-core中继承了SuppportActivity
 * 该类只要继承了setRootDelegare()。通过initContainer方法里的loadRootFrgment将id和setRootDelegare()方法绑定起来
 * 2.LatteDelegate 作用是:BaseDelegate extends SwipeBackFragment 其实这里是fragment
 */

public class ExampleActivity extends ProxyActivity implements
        ILauncherListener,
        ISignListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LancherDelegate();
//        return new LauncherScrollDelegate();
        return new SignUpDelegate();

    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注冊成功",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SINGED:
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }

    }
}
