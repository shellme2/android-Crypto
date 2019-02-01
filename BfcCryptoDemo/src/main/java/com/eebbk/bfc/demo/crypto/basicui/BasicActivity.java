package com.eebbk.bfc.demo.crypto.basicui;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author liuyewu
 * @company EEBBK
 * @function 基础activity
 * @date 2016/11/18
 */
public class BasicActivity extends AppCompatActivity {

    public <T extends View> T findView(@IdRes int resId){
        return (T)findViewById(resId);
    }

}
