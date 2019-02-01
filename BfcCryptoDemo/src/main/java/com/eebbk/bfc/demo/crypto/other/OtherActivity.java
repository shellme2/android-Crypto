package com.eebbk.bfc.demo.crypto.other;

import android.os.Bundle;
import android.view.View;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OtherActivity extends BasicActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        initView();
    }

    private void initView(){
        findViewById(R.id.get_path_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_path_btn:
                showFileChooser();
                break;
        }
    }

    private void showFileChooser(){
        try {
            String test="你好！";
            MessageDigest messageDigest= MessageDigest.getInstance("SHA-224");
            messageDigest.digest(test.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
