package com.eebbk.bfc.demo.crypto.basic.irreversible;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;
import com.eebbk.bfc.demo.crypto.util.FileUtils;
import com.eebbk.bfc.demo.crypto.util.Md5Utils;

import java.io.File;
import java.util.Arrays;

public class Md5Activity extends BasicActivity implements View.OnClickListener{

    private static final String FILE_PATH=Environment.getExternalStorageDirectory().getAbsolutePath()+"/cryptoTest.jpg";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);

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
                handleStringMd5();
                break;

            case R.id.md5_file_btn:
                handleFileMd5();
                break;

            case R.id.md5_string_check_btn:
                handleStringMd5Check();
                break;

            case R.id.md5_file_check_btn:
                handleFileMd5Check();
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
    private void handleStringMd5(){
        clearView();

        new Thread(new Runnable() {
            @Override
            public void run() {

                String data=null;

                if(isDefault){
                    data= mDefaultStringTv.getText().toString();
                }else if(isCustom){
                    data=mCustomStringEt.getText().toString();
                }

                if(!TextUtils.isEmpty(data)){
                    stringtv=Md5Utils.getMd5String(data);
                    bytetv=Arrays.toString(Md5Utils.getMd5(data));
                }else {
                    stringtv="the resource is null";
                    bytetv="the resource is null";
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

    private void handleFileMd5(){
        clearView();

        new Thread(new Runnable() {
            @Override
            public void run() {

                File file=null;

                if(isDefault){
                    file=new File(FILE_PATH);
                }else if(isCustom){
                    String path=mFilePathTv.getText().toString().trim();
                    file=new File(path);
                }

                if(file!=null&&file.exists()){
                    stringtv=Md5Utils.getFileMd5String(file);
                    bytetv=Arrays.toString(Md5Utils.getFileMd5(file));
                }else{
                    stringtv="file null";
                    stringtv="file null";
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
    private void handleStringMd5Check(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    flag=Md5Utils.checkMd5(mDefaultStringTv.getText().toString(),mMd5StringTv.getText().toString());
                }else if(isCustom){
                    flag=Md5Utils.checkMd5(mCustomStringEt.getText().toString(),mMd5StringTv.getText().toString());
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

    private void handleFileMd5Check(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isDefault){
                    flag=Md5Utils.checkFileMd5(FILE_PATH,mMd5StringTv.getText().toString());
                }else if(isCustom){
                    String path=mFilePathTv.getText().toString().trim();
                    flag=Md5Utils.checkFileMd5(path,mMd5StringTv.getText().toString());
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
                    String path = FileUtils.getPathFormSelector(this, uri);

                    mFilePathTv.setText(path);
//                    Log.i("liuyewu", "path==" + path);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
