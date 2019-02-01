package com.eebbk.bfc.demo.crypto.basic.irreversible;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.eebbk.bfc.crypto.irreversible.SHACrypto;
import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.FileUtils;
import com.eebbk.bfc.demo.crypto.util.SHAUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class ShaActivity extends BasicActivity implements View.OnClickListener {

    private static final String FILE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/cryptoTest.jpg";

    private Button mDefaultBtn;
    private Button mCustomBtn;

    private LinearLayout mDefaultLl;
    private LinearLayout mCustomLl;
    private EditText mCustomStringEt;
    private Button mFileChooseBtn;
    private TextView mFilePathTv;
    private TextView mDefaultStringTv;

    private Button mStringMd5Btn;
    private Button mFileMd5Btn;
    private TextView mMd5StringTv;
    private TextView mMd5ByteTv;

    private Button mStringMd5CheckBtn;
    private Button mFileMd5CheckBtn;
    private TextView mStringMd5CheckBTv;
    private TextView mFileMd5CheckTv;

//    private ExplorerDialog mAddDialog;
//    private ArrayList<File> mFileList = new ArrayList<>();

    private boolean isDefault;
    private boolean isCustom;

    private Spinner mModelSp;
    private String[] MODE=new String[]{SHACrypto.SHAAlgorithm.SHA1,
            SHACrypto.SHAAlgorithm.SHA224,
            SHACrypto.SHAAlgorithm.SHA256,
            SHACrypto.SHAAlgorithm.SHA384,
            SHACrypto.SHAAlgorithm.SHA512};
    //"SHA-224"在低版本的API中不能用，1–8,22+
    //特殊数据：SHA-224只在1-8,22+上有用，也就是说Android2.3到Android5.0上都不能用

    private String mCurrentMode;

//    private Handler myHandlernew =new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sha);

        initView();
        initStatic();
        initData();
    }

    private void initView(){
        mDefaultBtn=findView(R.id.default_btn);
        mCustomBtn=findView(R.id.custom_btn);

        mDefaultLl=findView(R.id.default_ll);
        mCustomLl =findView(R.id.custom_ll);
        mCustomStringEt=findView(R.id.custom_et);
        mFileChooseBtn=findView(R.id.choose_file_btn);
        mFilePathTv=findView(R.id.file_path_tv);
        mDefaultStringTv =findView(R.id.default_tv);

        mStringMd5Btn=findView(R.id.md5_string_btn);
        mFileMd5Btn=findView(R.id.md5_file_btn);
        mMd5StringTv=findView(R.id.md5_string_tv);
        mMd5ByteTv =findView(R.id.md5_byte_tv);

        mStringMd5CheckBtn=findView(R.id.md5_string_check_btn);
        mFileMd5CheckBtn=findView(R.id.md5_file_check_btn);
        mStringMd5CheckBTv=findView(R.id.md5_string_check_result_tv);
        mFileMd5CheckTv=findView(R.id.md5_file_check_result_tv);

        mModelSp=findView(R.id.mode_sp);
    }

    private void initStatic(){
        mDefaultBtn.setOnClickListener(this);
        mCustomBtn.setOnClickListener(this);
        mFileChooseBtn.setOnClickListener(this);
        mStringMd5Btn.setOnClickListener(this);
        mFileMd5Btn.setOnClickListener(this);
        mStringMd5CheckBtn.setOnClickListener(this);
        mFileMd5CheckBtn.setOnClickListener(this);

        mDefaultLl.setVisibility(View.VISIBLE);
        mCustomLl.setVisibility(View.GONE);
        mDefaultBtn.setEnabled(false);
        mCustomBtn.setEnabled(true);
        isDefault=true;
        isCustom=false;

//        mAddDialog = new ExplorerDialog(this, 10);

        initModeSp();
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

    private void initData(){
        File file = new File(FILE_PATH);
        if(!file.exists()){
            FileUtils.copyFromAssets(this, file);
        }
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

            case R.id.choose_file_btn:
                handleChooseFile();
                break;

            case R.id.md5_string_btn:
                handleStringSHA();
                break;

            case R.id.md5_file_btn:
                handleFileSHA();
                break;

            case R.id.md5_string_check_btn:
                handleStringSHACheck();
                break;

            case R.id.md5_file_check_btn:
                handleFileSHACheck();
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

    private void clearView(){
        mMd5StringTv.setText("");
        mMd5ByteTv.setText("");

        mStringMd5CheckBTv.setText("");
        mFileMd5CheckTv.setText("");
    }

    private void handleChooseFile(){
        showFileChooser();
    }

    private String stringtv ="";
    private String bytetv = "";
    private void handleStringSHA(){
        clearView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    String data= mDefaultStringTv.getText().toString();
                    if(!TextUtils.isEmpty(data)){
                        stringtv=SHAUtils.getSHAString("SHA-1",data);
                        bytetv=Arrays.toString(SHAUtils.getSHA("SHA-1",data));
                    }else{
                        stringtv="the resource is null";
                        bytetv="the resource is null";
                    }
                }else if(isCustom){
                    String data=mCustomStringEt.getText().toString();
                    if(!TextUtils.isEmpty(data)){
                        stringtv=SHAUtils.getSHAString(mCurrentMode,data);
                        bytetv=Arrays.toString(SHAUtils.getSHA(mCurrentMode,data));
                    }else{
                        stringtv="the resource is null";
                        bytetv="the resource is null";
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMd5StringTv.setText(stringtv);
                        mMd5ByteTv.setText(bytetv);
                    }
                });
            }
        }).start();

    }

    private void handleFileSHA(){
        clearView();

        new Thread(new Runnable() {
            @Override
            public void run() {

                if(isDefault){
                    File file=new File(FILE_PATH);
                    if(file!=null&&file.exists()){
                        stringtv=SHAUtils.getFileSHAString("SHA-1",file);
                        bytetv=Arrays.toString(SHAUtils.getFileSHA("SHA-1",file));
                    }else{
                        stringtv="file null";
                        bytetv="file null";
                    }
                }else if(isCustom){

                    String path=mFilePathTv.getText().toString().trim();
                    File file=new File(path);

                    if(file!=null&&file.exists()){
                        stringtv=SHAUtils.getFileSHAString(mCurrentMode,file);
                        bytetv=Arrays.toString(SHAUtils.getFileSHA(mCurrentMode,file));
                    }else{
                        stringtv="the resource is null";
                        bytetv="the resource is null";
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMd5StringTv.setText(stringtv);
                        mMd5ByteTv.setText(bytetv);
                    }
                });
            }
        }).start();

    }

    private boolean flag=false;
    private void handleStringSHACheck(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                if(isDefault){
                    flag=SHAUtils.checkSHA("SHA-1",mDefaultStringTv.getText().toString(),mMd5StringTv.getText().toString());
                }else if(isCustom){
                    flag=SHAUtils.checkSHA(mCurrentMode,mCustomStringEt.getText().toString(),mMd5StringTv.getText().toString());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mStringMd5CheckBTv.setText(String.valueOf(flag));
                    }
                });
            }
        }).start();
    }

    private void handleFileSHACheck(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                if(isDefault){
                    flag=SHAUtils.checkFileSHA("SHA-1",FILE_PATH,mMd5StringTv.getText().toString());
                }else if(isCustom){
                    String path=mFilePathTv.getText().toString().trim();
                    flag=SHAUtils.checkFileSHA(mCurrentMode,path,mMd5StringTv.getText().toString());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mFileMd5CheckTv.setText(String.valueOf(flag));
                    }
                });
            }
        }).start();
    }

    //选择文件
    private static final int FILE_SELECT_CODE = 101;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",  Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
//                    Log.i("liuyewu", "uri==" + uri.getEncodedPath());
                    final String path = FileUtils.getPathFormSelector(this, uri);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mFilePathTv.setText(path);
                        }
                    });

//                    Log.i("liuyewu", "path==" + path);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



//    private static final int WHAT_STRING_SHA=1;
//    private static final int WHAT_BYTE_SHA=1;
//
//    static class MyHandler extends Handler {
//        WeakReference<Activity> mActivityReference;
//
//        MyHandler(Activity act) {
//            mActivityReference = new WeakReference<>(act);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            ShaActivity act = (ShaActivity) mActivityReference.get();
//            if(act==null){
//                return;
//            }
//            switch (msg.what){
//                case WHAT_STRING_SHA:
//                    handleStringSHA();
//                    break;
//
//
//            }
//        }
//    }
}
