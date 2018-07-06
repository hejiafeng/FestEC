package com.diabin.latte.delegates;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diabin.latte.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Administrator on 2018/2/4.
 * BaseDelegate 抽象类
 * 抽象方法：setLayout()和onBindView(@Nullable Bundle savedInstanceState,View rootView)
 * 作用：继承者必须重写setLayout()和onBindView
 * setLayout()返回Object
 * onBindView传入参数Budle和View
 *onCreateView中将setLayout()和onBindView联系起来
 *
 * 注意：使用Ojbect，该对象是父类，使用instanceof判断类型
 * 凡是通过返回值的方法，应该判断该对象是否为空 比如if (rootView!=null) 避免空指针
 *
 * Unbinder使用方法
 *
 * 此类作用是让fragment继承，重写setLayout()---rootView和bindView Bundle与rootView建立联系
 *
 */

public abstract class BaseDelegate extends SwipeBackFragment{
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder=null;

    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=null;
        if (setLayout() instanceof Integer){
            rootView=inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView= (View) setLayout();
        }else {
            throw new ClassCastException("setLayout() type must be int or View;");
        }
        if (rootView!=null){
            mUnbinder= ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }
        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
