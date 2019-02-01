package com.eebbk.bfc.demo.crypto.basic;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.IntentUtils;

public class BasicFunctionTestActivity extends BasicActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_fanction);

        initView();
    }

    private void initView(){
        findView(R.id.algorithm_btn).setOnClickListener(this);
        findView(R.id.version_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.algorithm_btn:
                IntentUtils.gotoAlgorithmTest(this);
                break;

            case R.id.version_btn:
                IntentUtils.gotoVersionInfoTest(this);
                break;

        }
    }
}
