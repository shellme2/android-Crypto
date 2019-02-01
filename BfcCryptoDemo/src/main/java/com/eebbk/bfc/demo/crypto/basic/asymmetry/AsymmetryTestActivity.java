package com.eebbk.bfc.demo.crypto.basic.asymmetry;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.IntentUtils;

public class AsymmetryTestActivity extends BasicActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asymmetry_test);

        initView();
    }

    private void initView(){
        findView(R.id.rsa_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rsa_btn:
                IntentUtils.gotoRsaTest(this);
                break;

        }
    }
}
