package com.eebbk.bfc.demo.crypto.basic.asymmetry;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.eebbk.bfc.crypto.asymmetry.RSACrypto;
import com.eebbk.bfc.crypto.base64.binary.Base64;
import com.eebbk.bfc.crypto.secretkey.asymmetry.RSAKeyManager;
import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.RSAUtils;
import com.eebbk.bfc.demo.crypto.util.StringUtils;

import java.lang.ref.WeakReference;
import java.util.Arrays;

public class RsaActivity extends BasicActivity implements View.OnClickListener {

    private Button mDefaultBtn;
    private Button mCustomBtn;
    private Button mGeneratorBtn;

    private TextView mPrivateKeyTv;
    private TextView mPublicKeyTv;

    private LinearLayout mDefaultLl;
    private TextView mDefaultStringTv;

    private LinearLayout mCustomLl;
    private Spinner mModelSp;
    private Spinner mPaddingSp;
    private EditText mCustomStringTv;

    private Button mPublicEncryptBtn;
    private TextView mPublicStringTv;
    private TextView mPublicByteTv;
    private TextView mCountTv;

    private Button mPrivateDecryptBtn;
    private TextView mPrivateDeStringTv;
    private TextView mPrivateDeByteTv;

    private Button mPrivateEncryptBtn;
    private TextView mPrivateStringTv;
    private TextView mPrivateByteTv;

    private Button mPublicDecryptBtn;
    private TextView mPublicDeStringTv;
    private TextView mPublicDeByteTv;

    private boolean isDefault;
    private boolean isCustom;

    private Spinner mKeyLengthSp;

    private String[] MODE=new String[]{
            RSACrypto.AsymmetryMode.ECB,
            RSACrypto.AsymmetryMode.NONE};

    private String[] PADDIND=new String[]{
            RSACrypto.AsymmetryPadding.NO_PADDING,
            RSACrypto.AsymmetryPadding.PKCS1_PADDING,
            RSACrypto.AsymmetryPadding.OAEP_PADDING,
            RSACrypto.AsymmetryPadding.OAEP_WITH_SHA1_AND_MGF1_PADDING,
            RSACrypto.AsymmetryPadding.OAEP_WITH_SHA256_AND_MGF1_PADDING};

    private String[] KEY_LENGTH=new String[]{
            RSAKeyManager.RASKeyLength.LENGTH_1024+"",
            RSAKeyManager.RASKeyLength.LENGTH_2048+"",
            RSAKeyManager.RASKeyLength.LENGTH_3072+"",
            RSAKeyManager.RASKeyLength.LENGTH_4096+"",
            RSAKeyManager.RASKeyLength.LENGTH_5120+"",
            RSAKeyManager.RASKeyLength.LENGTH_6144+"",
            RSAKeyManager.RASKeyLength.LENGTH_7168+"",
            RSAKeyManager.RASKeyLength.LENGTH_8192+"",
            RSAKeyManager.RASKeyLength.LENGTH_9216+"",
            RSAKeyManager.RASKeyLength.LENGTH_10240+""};//64*6~64*256

    private String mCurrentMode;
    private String mCurrentPadding;
    private int mCurrentKeyLenth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);

        initView();
        initStatic();
    }

    private void initView(){
        mDefaultBtn=findView(R.id.default_btn);
        mCustomBtn=findView(R.id.custom_btn);
        mGeneratorBtn=findView(R.id.key_generator_btn);

        mPrivateKeyTv=findView(R.id.private_key_tv);
        mPublicKeyTv=findView(R.id.public_key_tv);

        mDefaultLl=findView(R.id.default_ll);
        mDefaultStringTv =findView(R.id.default_tv);

        mCustomLl =findView(R.id.custom_ll);
        mModelSp=findView(R.id.mode_sp);
        mPaddingSp=findView(R.id.padding_sp);
        mCustomStringTv=findView(R.id.custom_et);

        mPublicEncryptBtn=findView(R.id.public_encrypt_btn);
        mPrivateDecryptBtn=findView(R.id.private_decrypt_btn);
        mPublicStringTv=findView(R.id.public_string_tv);
        mCountTv=findView(R.id.count_tv);

        mPublicByteTv=findView(R.id.public_byte_tv);
        mPrivateDeStringTv=findView(R.id.private_de_string_tv);
        mPrivateDeByteTv=findView(R.id.private_de_byte_tv);

        mPrivateEncryptBtn=findView(R.id.private_encrypt_btn);
        mPublicDecryptBtn=findView(R.id.public_decrypt_btn);
        mPrivateStringTv=findView(R.id.private_string_tv);
        mPrivateByteTv=findView(R.id.private_byte_tv);
        mPublicDeStringTv=findView(R.id.public_de_string_tv);
        mPublicDeByteTv=findView(R.id.public_de_byte_tv);

        mKeyLengthSp=findView(R.id.key_length_sp);
    }

    private void initStatic(){
        mDefaultBtn.setOnClickListener(this);
        mCustomBtn.setOnClickListener(this);
        mGeneratorBtn.setOnClickListener(this);

        mPublicEncryptBtn.setOnClickListener(this);
        mPublicDecryptBtn.setOnClickListener(this);
        mPrivateEncryptBtn.setOnClickListener(this);
        mPrivateDecryptBtn.setOnClickListener(this);

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
                mCurrentKeyLenth=Integer.valueOf(KEY_LENGTH[position]);
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

            case R.id.key_generator_btn:
                handleKeyGenerator();
                break;

            case R.id.public_encrypt_btn:
                handlePublicEncrypt();
                break;

            case R.id.private_decrypt_btn:
                handlePrivateDecrypt();
                break;

            case R.id.private_encrypt_btn:
                handlePrivateEncrypt();
                break;

            case R.id.public_decrypt_btn:
                handlePublicDecrypt();
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

        clearView();
    }

    private void handleCustom(){
        mDefaultLl.setVisibility(View.GONE);
        mCustomLl.setVisibility(View.VISIBLE);
        mDefaultBtn.setEnabled(true);
        mCustomBtn.setEnabled(false);
        isDefault=false;
        isCustom=true;

        clearView();
    }

    private String mCurrentPublicKey;
    private String mCurrentPrivateKey;

    String resultString=null;
    byte[] resultByte=null;
    private void handlePublicEncrypt(){
        String publicKey=mPrivateKeyTv.getText().toString().trim();
        if(TextUtils.isEmpty(publicKey)){
            mPublicStringTv.setText("the publicKey is null");
            mPublicByteTv.setText("the publicKey is null");
            return;
        }

        resultString=null;
        resultByte=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    String data=mDefaultStringTv.getText().toString().trim();

                    resultString= RSAUtils.encryptByPublicKeyToBase64String(mCurrentPublicKey,data.getBytes());
                    resultByte=RSAUtils.encryptByPublicKey(mCurrentPublicKey,data.getBytes());

                }else if(isCustom){
                    RSACrypto crypto=new RSACrypto(mCurrentMode,mCurrentPadding);

                    String data=mCustomStringTv.getText().toString().trim();
                    try{
                        resultString=crypto.encryptByPublicKeyToBase64String(mCurrentPublicKey,data.getBytes());
                        resultByte=crypto.encryptByPublicKey(mCurrentPublicKey,data.getBytes());
                    }catch (Exception e){
                        Log.e("liuyewu",e.getMessage());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(isDefault){
                            mCountTv.setText(""+mDefaultStringTv.getText().toString().trim().getBytes().length);
                        }else if(isCustom){
                            mCountTv.setText(""+mCustomStringTv.getText().toString().trim().getBytes().length);
                        }

                        if(!TextUtils.isEmpty(resultString)){
                            mPublicStringTv.setText(resultString);
                        }else{
                            mPublicStringTv.setText("the resource is null");
                        }

                        if(resultByte!=null&&resultByte.length>0){
                            mPublicByteTv.setText(Arrays.toString(resultByte));
                        }else{
                            mPublicByteTv.setText("the resource is null");
                        }
                    }
                });

            }
        }).start();

    }

    byte[] resultString1=null;
    byte[] resultByte1=null;
    private void handlePrivateDecrypt(){
        String privateKey=mPrivateKeyTv.getText().toString().trim();
        final String publicByte=mPublicByteTv.getText().toString().trim();
        final String publicString=mPublicStringTv.getText().toString().trim();
        if(TextUtils.isEmpty(privateKey)||TextUtils.isEmpty(publicByte)||TextUtils.isEmpty(publicString)){
            mPrivateDeStringTv.setText("the privateKey is null");
            mPrivateDeByteTv.setText("the privateKey is null");
            return;
        }

        resultString1=null;
        resultByte1=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    resultString1=RSAUtils.decryptByPrivateKeyFromBase64String(mCurrentPrivateKey,
                            publicString);
                    resultByte1=RSAUtils.decryptByPrivateKey(mCurrentPrivateKey,
                            StringUtils.getBytesFromString(publicByte));
                }else if(isCustom){
                    RSACrypto crypto=new RSACrypto(mCurrentMode,mCurrentPadding);

                    try{
                        resultByte1=crypto.decryptByPrivateKey(mCurrentPrivateKey,
                                StringUtils.getBytesFromString(publicByte));
                        resultString1=crypto.decryptByPrivateKeyFromBase64String(mCurrentPrivateKey,
                                publicString);
                    }catch (Exception e){
                        Log.e("liuyewu",e.getMessage());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(resultString1!=null&&resultString1.length>0){
                            mPrivateDeStringTv.setText(new String(resultString1));
                        }else{
                            mPrivateDeStringTv.setText("the resource is null");
                        }

                        if(resultByte1!=null&&resultByte1.length>0){
                            mPrivateDeByteTv.setText(new String(resultByte1));
                        }else{
                            mPrivateDeByteTv.setText("the resource is null");
                        }
                    }
                });
            }
        }).start();

    }

    private void handlePrivateEncrypt(){
        String privateKey=mPrivateKeyTv.getText().toString().trim();

        if(TextUtils.isEmpty(privateKey)){
            mPrivateStringTv.setText("the privateKey is null");
            mPrivateByteTv.setText("the privateKey is null");
            return;
        }

        resultString=null;
        resultByte=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    String data=mDefaultStringTv.getText().toString().trim();
                    resultString= RSAUtils.encryptByPrivateKeyToBase64String(mCurrentPrivateKey,data.getBytes());
                    resultByte=RSAUtils.encryptByPrivateKey(mCurrentPrivateKey,data.getBytes());
                }else if(isCustom){
                    RSACrypto crypto=new RSACrypto(mCurrentMode,mCurrentPadding);

                    String data=mCustomStringTv.getText().toString().trim();
                    try{
                        resultString=crypto.encryptByPrivateKeyToBase64String(mCurrentPrivateKey,data.getBytes());
                        resultByte=crypto.encryptByPrivateKey(mCurrentPrivateKey,data.getBytes());
                    }catch (Exception e){
                        Log.e("liuyewu",e.getMessage());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!TextUtils.isEmpty(resultString)){
                            mPrivateStringTv.setText(resultString);
                        }else{
                            mPrivateStringTv.setText("the resource is null");
                        }

                        if(resultByte!=null&&resultByte.length>0){
                            mPrivateByteTv.setText(Arrays.toString(resultByte));
                        }else{
                            mPrivateByteTv.setText("the resource is null");
                        }
                    }
                });
            }
        }).start();

    }

    private void handlePublicDecrypt(){
        String publicKey=mPublicKeyTv.getText().toString().trim();
        final String privateString=mPrivateStringTv.getText().toString().trim();
        final String privateByte=mPrivateByteTv.getText().toString().trim();
        if(TextUtils.isEmpty(publicKey)||TextUtils.isEmpty(privateString)||TextUtils.isEmpty(privateByte)){
            mPublicDeStringTv.setText("the publicKey is null");
            mPublicDeByteTv.setText("the publicKey is null");
            return;
        }

        resultString1=null;
        resultByte1=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    resultString1=RSAUtils.decryptByPublicKeyFromBase64String(mCurrentPublicKey,
                            privateString);
                    resultByte1=RSAUtils.decryptByPublicKey(mCurrentPublicKey,
                            StringUtils.getBytesFromString(privateByte));
                }else if(isCustom){
                    RSACrypto crypto=new RSACrypto(mCurrentMode,mCurrentPadding);

                    try{
                        resultByte1=crypto.decryptByPublicKey(mCurrentPublicKey,
                                StringUtils.getBytesFromString(privateByte));
                        resultString1=crypto.decryptByPublicKeyFromBase64String(mCurrentPublicKey,
                                privateString);
                    }catch (Exception e){
                        Log.e("liuyewu",e.getMessage());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(resultString1!=null&&resultString1.length>0){
                            mPublicDeStringTv.setText(new String(resultString1));
                        }else{
                            mPublicDeStringTv.setText("the resource is null");
                        }

                        if(resultByte1!=null&&resultByte1.length>0){
                            mPublicDeByteTv.setText(new String(resultByte1));
                        }else{
                            mPublicDeByteTv.setText("the resource is null");
                        }
                    }
                });
            }
        }).start();

    }

    private void handleKeyGenerator(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                RSAKeyManager rsaKeyManager= null;
                try {
                    rsaKeyManager = new RSAKeyManager(mCurrentKeyLenth);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mCurrentPrivateKey=Base64.encodeBase64String(rsaKeyManager.getPrivateKey().getEncoded());
                mCurrentPublicKey=Base64.encodeBase64String(rsaKeyManager.getPublicKey().getEncoded());

                Message msg=new Message();
                msg.what=WHAT_KEY;
                Bundle bundle=new Bundle();
                bundle.putString("mCurrentPrivateKey",mCurrentPrivateKey);
                bundle.putString("mCurrentPublicKey",mCurrentPublicKey);
                msg.setData(bundle);
                myHandler.sendMessage(msg);
            }
        }).start();

        mPrivateKeyTv.setText("");
        mPublicKeyTv.setText("");
        clearView();
    }

    private void clearView(){
        mPublicStringTv.setText("");
        mPublicByteTv.setText("");

        mPublicDeStringTv.setText("");
        mPublicDeByteTv.setText("");

        mPrivateStringTv.setText("");
        mPrivateByteTv.setText("");

        mPrivateDeStringTv.setText("");
        mPrivateDeByteTv.setText("");
    }

    private static final int WHAT_KEY=1;
    private MyHandler myHandler=new MyHandler(this);
    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(Activity act) {
            mActivityReference = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            RsaActivity act = (RsaActivity) mActivityReference.get();
            if(act==null){
                return;
            }
            switch (msg.what){
                case WHAT_KEY:
                    Bundle bundleKey=msg.getData();
                    String privateKey=bundleKey.getString("mCurrentPrivateKey");
                    String publicKey=bundleKey.getString("mCurrentPublicKey");
                    act.mPrivateKeyTv.setText(privateKey+" : "+privateKey.length());
                    act.mPublicKeyTv.setText(publicKey+" : "+publicKey.length());
                    break;

            }
        }
    }

}
