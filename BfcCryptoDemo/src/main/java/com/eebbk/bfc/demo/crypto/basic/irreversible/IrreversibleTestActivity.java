package com.eebbk.bfc.demo.crypto.basic.irreversible;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.IntentUtils;

public class IrreversibleTestActivity extends BasicActivity implements
        View.OnClickListener {

    private String TAG=IrreversibleTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irreversible_test);

        initView();
    }

    private void initView(){
        findView(R.id.md5_btn).setOnClickListener(this);
        findView(R.id.sha_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.md5_btn:
                IntentUtils.gotoMd5Test(this);
                break;

            case R.id.sha_btn:
                IntentUtils.gotoShaTest(this);
                break;
        }
    }
}
