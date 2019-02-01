package com.eebbk.bfc.demo.crypto.basic.symmetry;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.eebbk.bfc.crypto.secretkey.symmetry.DESKeyManager;
import com.eebbk.bfc.crypto.symmetry.DESCrypto;
import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.DESUtils;
import com.eebbk.bfc.demo.crypto.util.StringUtils;

import java.util.Arrays;

public class DesActivity extends BasicActivity implements View.OnClickListener{

    private Button mDefaultBtn;
    private Button mCustomBtn;

    private LinearLayout mDefaultLl;
    private TextView mDefaultStringTv;

    private LinearLayout mCustomLl;
    private Spinner mModelSp;
    private Spinner mPaddingSp;
    private EditText mIvEt;
    private EditText mSecretEt;
    private EditText mCustomStringTv;

    private Button mEncryptBtn;
    private TextView mStringTv;
    private TextView mByteTv;
    private TextView mCountTv;

    private Button mDecryptBtn;
    private TextView mDeStringTv;
    private TextView mDeByteTv;

    private boolean isDefault;
    private boolean isCustom;

    private Button mKeyAutoBtn;
    private Button mKeyCustomBtn;

    private LinearLayout mKeyCustomLl;
    private LinearLayout mKeyAutoLl;
    private LinearLayout mKeyAutoShowLl;
    private LinearLayout mKeyAutoCreateLl;
    private TextView mSecretTv;
    private Button mKeyOkBtn;
    private Spinner mKeyLengthSp;

    private boolean isKeyAuto=false;
    private boolean isKeyCustom=true;

    private String[] MODE=new String[]{DESCrypto.SymmetryMode.CBC,
            DESCrypto.SymmetryMode.ECB,
            DESCrypto.SymmetryMode.CTR,
            DESCrypto.SymmetryMode.CTS,
            DESCrypto.SymmetryMode.CFB,
            DESCrypto.SymmetryMode.OFB};
            // "CFB8","CFB16","CFB32","CFB64","OFB8","OFB16","OFB32","OFB64"

    private String[] PADDIND=new String[]{
            DESCrypto.SymmetryPadding.PKCS5_PADDING,
            DESCrypto.SymmetryPadding.NO_PADDING,
            DESCrypto.SymmetryPadding.ISO10126_PADDING};//,"PKCS7PADDING"

    private String[] KEY_LENGTH=new String[]{DESKeyManager.DES_KEY_LENGTH+""};

    private String mCurrentMode;
    private String mCurrentPadding;
    private int mCurrentKeyLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);

        initView();
        initStatic();
    }

    private void initView(){
        mDefaultBtn=findView(R.id.default_btn);
        mCustomBtn=findView(R.id.custom_btn);

        mDefaultLl=findView(R.id.default_ll);
        mDefaultStringTv =findView(R.id.default_tv);

        mCustomLl =findView(R.id.custom_ll);
        mModelSp=findView(R.id.mode_sp);
        mPaddingSp=findView(R.id.padding_sp);
        mIvEt=findView(R.id.iv_et);
        mSecretEt=findView(R.id.secret_et);
        mCustomStringTv=findView(R.id.custom_et);

        mEncryptBtn=findView(R.id.encrypt_btn);
        mDecryptBtn=findView(R.id.decrypt_btn);
        mStringTv=findView(R.id.string_tv);
        mCountTv=findView(R.id.count_tv);
        mByteTv=findView(R.id.byte_tv);
        mDeStringTv=findView(R.id.de_string_tv);
        mDeByteTv=findView(R.id.de_byte_tv);

        mKeyAutoBtn=findView(R.id.key_auto_btn);
        mKeyCustomBtn=findView(R.id.key_custom_btn);

        mKeyAutoLl=findView(R.id.key_auto_ll);
        mKeyAutoShowLl=findView(R.id.key_auto_show_ll);
        mKeyAutoCreateLl=findView(R.id.key_auto_create_ll);
        mSecretTv=findView(R.id.secret_tv);

        mKeyCustomLl=findView(R.id.key_custom_ll);
        mKeyLengthSp=findView(R.id.key_length_sp);
        mKeyOkBtn=findView(R.id.key_ok_btn);
    }

    private void initStatic(){
        mDefaultBtn.setOnClickListener(this);
        mCustomBtn.setOnClickListener(this);
        mEncryptBtn.setOnClickListener(this);
        mDecryptBtn.setOnClickListener(this);

        mKeyAutoBtn.setOnClickListener(this);
        mKeyCustomBtn.setOnClickListener(this);
        mKeyOkBtn.setOnClickListener(this);

        mKeyCustomLl.setVisibility(View.VISIBLE);
        mKeyAutoLl.setVisibility(View.GONE);
        mKeyCustomBtn.setEnabled(false);
        mKeyAutoBtn.setEnabled(true);

        mDefaultLl.setVisibility(View.VISIBLE);
        mCustomLl.setVisibility(View.GONE);
        mDefaultBtn.setEnabled(false);
        mCustomBtn.setEnabled(true);
        isDefault=true;
        isCustom=false;

        initModeSp();
        initPaddingSp();
        initKeyLength();
    }

    private void initModeSp(){
        // 将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(),
                android.R.layout.simple_spinner_item,
                MODE);
        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mModelSp.setAdapter(adapter);
        // 将adapter 添加到spinner中
        mModelSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentMode=MODE[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // 设置默认值
        mModelSp.setVisibility(View.VISIBLE);
    }

    private void initPaddingSp(){
        // 将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(),
                android.R.layout.simple_spinner_item,
                PADDIND);
        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPaddingSp.setAdapter(adapter);
        // 将adapter 添加到spinner中
        mPaddingSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentPadding=PADDIND[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // 设置默认值
        mPaddingSp.setVisibility(View.VISIBLE);
    }

    private void initKeyLength(){
        // 将可选内容与ArrayAdapter连接起来
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(),
                android.R.layout.simple_spinner_item,
                KEY_LENGTH);
        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKeyLengthSp.setAdapter(adapter);
        // 将adapter 添加到spinner中
        mKeyLengthSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentKeyLength =Integer.valueOf(KEY_LENGTH[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // 设置默认值
        mKeyLengthSp.setVisibility(View.VISIBLE);
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

            case R.id.key_auto_btn:
                handleKeyAuto();
                break;

            case R.id.key_custom_btn:
                handleKeyCustom();
                break;

            case R.id.key_ok_btn:
                handleKeyOk();
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
                String resultString=null;
                byte[] resultByte=null;
                long length=0;

                if(isDefault){
                    String data=mDefaultStringTv.getText().toString().trim();
                    try{
                        resultString=DESUtils.encrypt(data);
                        byte[] bytesDate=data.getBytes("utf-8");
                        length=bytesDate.length;
                        resultByte=DESUtils.encrypt(bytesDate);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else if(isCustom){
//            String key=mSecretEt.getText().toString().trim();
                    String iv=mIvEt.getText().toString().trim();
                    DESCrypto crypto=new DESCrypto(getKey(),mCurrentMode,mCurrentPadding,iv.getBytes());

                    String data=mCustomStringTv.getText().toString().trim();
                    try{
                        byte[] bytesDate=data.getBytes("utf-8");
                        length=bytesDate.length;
                        resultString=crypto.encryptToString(bytesDate);
                        resultByte=crypto.encrypt(bytesDate);
                    }catch (Exception e){
                        Log.d("liuyewu",e.getMessage());
                    }

                }

                final String resultStringTemp=resultString;
                final byte[] resultByteTemp=resultByte;
                final long lengthTemp=length;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCountTv.setText(""+lengthTemp);
                        if(!TextUtils.isEmpty(resultStringTemp)){
                            mStringTv.setText(resultStringTemp);
                        }else{
                            mStringTv.setText("the resource is null");
                        }

                        if(resultByteTemp!=null&&resultByteTemp.length>0){
                            mByteTv.setText(Arrays.toString(resultByteTemp));
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
                String resultString=null;
                byte[] resultByte=null;

                String encryptString=mStringTv.getText().toString().trim();
                String encryptByte=mByteTv.getText().toString().trim();
                if(TextUtils.isEmpty(encryptString)||TextUtils.isEmpty(encryptByte)){
                    mDeStringTv.setText("the resource is null");
                    mDeByteTv.setText("the resource is null");
                    return;
                }

                if(isDefault){
                    resultString=DESUtils.decrypt(encryptString);
                    resultByte=DESUtils.decrypt(StringUtils.getBytesFromString(encryptByte));
                }else if(isCustom){
//            String key=mSecretEt.getText().toString().trim();
                    String iv=mIvEt.getText().toString().trim();
                    DESCrypto crypto=new DESCrypto(getKey(),mCurrentMode,mCurrentPadding,iv.getBytes());

                    try{
                        resultByte=crypto.decrypt(StringUtils.getBytesFromString(encryptByte));
                        resultString=crypto.decryptToString(encryptString.getBytes());

                    }catch (Exception e){
                        Log.d("liuyewu",e.getMessage());
                    }
                }

                final String resultStringTemp=resultString;
                final byte[] resultByteTemp=resultByte;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!TextUtils.isEmpty(resultStringTemp)){
                            mDeStringTv.setText(resultStringTemp);
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

    private void handleKeyAuto(){
        isKeyAuto=true;
        isKeyCustom=false;

        mKeyCustomLl.setVisibility(View.GONE);
        mKeyAutoLl.setVisibility(View.VISIBLE);

        mKeyAutoShowLl.setVisibility(View.GONE);
        mKeyAutoCreateLl.setVisibility(View.VISIBLE);

        mKeyCustomBtn.setEnabled(true);
        mKeyAutoBtn.setEnabled(false);
    }

    private void handleKeyCustom(){
        isKeyAuto=false;
        isKeyCustom=true;

        mKeyCustomLl.setVisibility(View.VISIBLE);
        mKeyAutoLl.setVisibility(View.GONE);
        mKeyCustomBtn.setEnabled(false);
        mKeyAutoBtn.setEnabled(true);
    }
    private void handleKeyOk(){
        mKeyAutoShowLl.setVisibility(View.VISIBLE);
        mKeyAutoCreateLl.setVisibility(View.GONE);
//        mKeyAutoBtn.setVisibility(View.VISIBLE);

        DESKeyManager deSKeyManager= null;
        try {
            deSKeyManager = new DESKeyManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("liuyewu","length=="+mCurrentKeyLength);
        String key=Arrays.toString(deSKeyManager.getSecretKey().getEncoded());//new String(deSedeKeyManager.getSecretKey().getEncoded());//

        mSecretTv.setText(key);
    }

    private byte[] getKey(){
        byte[] key=null;
        if(isKeyCustom){
            key=mSecretEt.getText().toString().trim().getBytes();
        }else if(isKeyAuto){
            key=StringUtils.getBytesFromString(mSecretTv.getText().toString().trim());
        }
        return key;
    }

}
