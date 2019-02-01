package com.eebbk.bfc.demo.crypto.basic.symmetry;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.IntentUtils;

public class SymmetryTestActivity extends BasicActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symmetry_test);

        initView();
    }

    private void initView(){
        findView(R.id.des_btn).setOnClickListener(this);
        findView(R.id.aes_btn).setOnClickListener(this);
        findView(R.id.desede_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.des_btn:
                IntentUtils.gotoDesTest(this);
                break;

            case R.id.aes_btn:
                IntentUtils.gotoAesTest(this);
                break;

            case R.id.desede_btn:
                IntentUtils.gotoDesedeTest(this);
                break;
        }
    }
}
