package com.eebbk.bfc.demo.crypto.basicui;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.util.IntentUtils;

public class MainActivity extends BasicActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        findView(R.id.basic_function_test_btn).setOnClickListener(this);
        findView(R.id.safe_test_btn).setOnClickListener(this);
        findView(R.id.performance_test_btn).setOnClickListener(this);
        findView(R.id.limit_test_btn).setOnClickListener(this);
        findView(R.id.other_test_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.basic_function_test_btn:
                IntentUtils.gotoBasicFunctionTest(this);
                break;

            case R.id.safe_test_btn:
                IntentUtils.gotoSafeTest(this);
                break;

            case R.id.performance_test_btn:
                IntentUtils.gotoPerformanceTest(this);
                break;

            case R.id.limit_test_btn:
                IntentUtils.gotoLimitTest(this);
                break;

            case R.id.other_test_btn:
                IntentUtils.gotoOtherTest(this);
                break;
        }
    }

}
