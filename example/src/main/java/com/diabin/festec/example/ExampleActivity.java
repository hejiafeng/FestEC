package com.diabin.festec.example;

import com.diabin.latte.activities.ProxyActivity;
import com.diabin.latte.delegates.LatteDelegate;

/**
 * Created by Administrator on 2018/2/16.
 * 1,先写ProxyActivity,proxyActivity在latte-core中继承了SuppportActivity
 * 该类只要继承了setRootDelegare()。通过initContainer方法里的loadRootFrgment将id和setRootDelegare()方法绑定起来
 *2.LatteDelegate 作用是:BaseDelegate extends SwipeBackFragment 其实这里是fragment
 */

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();

    }

}
