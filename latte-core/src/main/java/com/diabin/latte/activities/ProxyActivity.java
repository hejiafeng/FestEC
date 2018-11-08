package com.diabin.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.diabin.latte.R;
import com.diabin.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2018/2/4.
 *1.该类只要继承了setRootDelegare()。通过initContainer方法里的loadRootFrgment将id和setRootDelegare()方法绑定起来
 */

public abstract class ProxyActivity extends SupportActivity{
    //  LatteDelegate 实际就是fragment
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        //FrameLayout
        final ContentFrameLayout container=new ContentFrameLayout(this);
        //设置id
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
