package com.eebbk.bfc.demo.crypto.basic.base64;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.crypto.base64.binary.Base64;
import com.eebbk.bfc.demo.crypto.util.StringUtils;

import java.util.Arrays;

public class Base64TestActivity extends BasicActivity implements View.OnClickListener {

    private Button mDefaultBtn;
    private Button mCustomBtn;

    private LinearLayout mDefaultLl;
    private TextView mDefaultStringTv;
    private LinearLayout mCustomLl;
    private EditText mCustomStringTv;

    private Button mEncryptBtn;
    private TextView mStringTv;
    private TextView mByteTv;

    private Button mDecryptBtn;
    private TextView mDeStringTv;
    private TextView mDeByteTv;

    private boolean isDefault;
    private boolean isCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base64_test);

        initView();
        initStatic();
    }

    private void initView(){
        mDefaultBtn=findView(R.id.default_btn);
        mCustomBtn=findView(R.id.custom_btn);

        mDefaultLl=findView(R.id.default_ll);
        mDefaultStringTv =findView(R.id.default_tv);

        mCustomLl =findView(R.id.custom_ll);
        mCustomStringTv=findView(R.id.custom_et);

        mEncryptBtn=findView(R.id.encrypt_btn);
        mDecryptBtn=findView(R.id.decrypt_btn);
        mStringTv=findView(R.id.string_tv);
        mByteTv=findView(R.id.byte_tv);
        mDeStringTv=findView(R.id.de_string_tv);
        mDeByteTv=findView(R.id.de_byte_tv);
    }

    private void initStatic(){
        mDefaultBtn.setOnClickListener(this);
        mCustomBtn.setOnClickListener(this);
        mEncryptBtn.setOnClickListener(this);
        mDecryptBtn.setOnClickListener(this);

        mDefaultLl.setVisibility(View.VISIBLE);
        mCustomLl.setVisibility(View.GONE);
        mDefaultBtn.setEnabled(false);
        mCustomBtn.setEnabled(true);
        isDefault=true;
        isCustom=false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.default_btn:
                handleDefault();
                break;

            case R.id.custom_btn:
                handleCustom();
                break;

            case R.id.encrypt_btn:
                handleEncrypt();
                break;

            case R.id.decrypt_btn:
                handleDecrypt();
                break;
        }
    }

    private void handleDefault(){
        mDefaultLl.setVisibility(View.VISIBLE);
        mCustomLl.setVisibility(View.GONE);
        mDefaultBtn.setEnabled(false);
        mCustomBtn.setEnabled(true);
        isDefault=true;
        isCustom=false;

        clearEncrypt();
        clearDecrypt();
    }

    private void handleCustom(){
        mDefaultLl.setVisibility(View.GONE);
        mCustomLl.setVisibility(View.VISIBLE);
        mDefaultBtn.setEnabled(true);
        mCustomBtn.setEnabled(false);
        isDefault=false;
        isCustom=true;

        clearEncrypt();
        clearDecrypt();
    }

    private void clearEncrypt(){
        mStringTv.setText("");
        mByteTv.setText("");
    }

    private void clearDecrypt(){
        mDeStringTv.setText("");
        mDeByteTv.setText("");
    }

    private void handleEncrypt(){
        clearDecrypt();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String data=null;

                if(isDefault){
                    data=mDefaultStringTv.getText().toString().trim();
                }else if(isCustom){
                    data=mCustomStringTv.getText().toString().trim();
                }

                final String resultString= Base64.encodeBase64String(data.getBytes());
                final byte[] resultByte=Base64.encodeBase64(data.getBytes());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!TextUtils.isEmpty(resultString)){
                            mStringTv.setText(resultString);
                        }else{
                            mStringTv.setText("the resource is null");
                        }
                        if(resultByte!=null &&resultByte.length>0){
                            mByteTv.setText(Arrays.toString(resultByte));
                        }else{
                            mByteTv.setText("the resource is null");
                        }
                    }
                });

            }
        }).start();


    }

    private void handleDecrypt(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] resultString=null;
                byte[] resultByte=null;

                if(isDefault){
                    String temp=mStringTv.getText().toString().trim();
                    if(TextUtils.isEmpty(temp)||temp.equals("the resource is null")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDeStringTv.setText("the resource is null");
                            }
                        });

                        return;
                    }
                    resultString=Base64.decodeBase64(temp.getBytes());

                    String tempString=mByteTv.getText().toString().trim();
                    if(TextUtils.isEmpty(tempString)||temp.equals("the resource is null")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDeByteTv.setText("the resource is null");
                            }
                        });

                        return;
                    }
                    resultByte=Base64.decodeBase64(StringUtils.getBytesFromString(tempString));
                }else if(isCustom){
                    String temp=mStringTv.getText().toString().trim();
                    if(TextUtils.isEmpty(temp)||temp.equals("the resource is null")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDeStringTv.setText("the resource is null");
                            }
                        });

                        return;
                    }
                    resultString=Base64.decodeBase64(temp);

                    String tempString=mByteTv.getText().toString().trim();
                    if(TextUtils.isEmpty(tempString)||temp.equals("the resource is null")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDeByteTv.setText("the resource is null");
                            }
                        });

                        return;
                    }
                    resultByte=Base64.decodeBase64(StringUtils.getBytesFromString(tempString));
                }

                final byte[] resultStringTemp=resultString;
                final byte[] resultByteTemp=resultByte;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(resultStringTemp!=null&&resultStringTemp.length>0){
                            mDeStringTv.setText(new String(resultStringTemp));
                        }else{
                            mDeStringTv.setText("the resource is null");
                        }

                        if(resultByteTemp!=null&&resultByteTemp.length>0){
                            mDeByteTv.setText(new String(resultByteTemp));
                        }else{
                            mDeByteTv.setText("the resource is null");
                        }
                    }
                });

            }
        }).start();

    }

}
