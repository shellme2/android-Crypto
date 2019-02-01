package com.eebbk.bfc.demo.crypto.basic;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.IntentUtils;

public class AlgorithmTestActivity extends BasicActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algorithm_test);

        initView();
    }

    private void initView(){
        findView(R.id.irreversible_btn).setOnClickListener(this);
        findView(R.id.base64_btn).setOnClickListener(this);
        findView(R.id.asymmetry_btn).setOnClickListener(this);
        findView(R.id.symmetry_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.irreversible_btn:
                IntentUtils.gotoIrreversibleTest(this);
                break;

            case R.id.base64_btn:
                IntentUtils.gotoBase64Test(this);
                break;

            case R.id.asymmetry_btn:
                IntentUtils.gotoAsymmetryTest(this);
                break;

            case R.id.symmetry_btn:
                IntentUtils.gotoSymmetryTest(this);
                break;

        }
    }
}
