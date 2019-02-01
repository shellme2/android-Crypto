package com.eebbk.bfc.demo.crypto.basic;

import android.os.Bundle;
import android.widget.TextView;

import com.eebbk.bfc.demo.crypto.R;
import com.eebbk.bfc.demo.crypto.basicui.BasicActivity;

import com.eebbk.bfc.crypto.SDKVersion;

public class VersionInfoTestActivity extends BasicActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_info_test);

        initView();
    }

    private void initView(){
        TextView infoTv = findView(R.id.version_info_tv);
        StringBuilder sb = new StringBuilder();
        sb.append("库名称: " + SDKVersion.getLibraryName());
        sb.append("\r\n \r\n版本序号: " + SDKVersion.getSDKInt());
        sb.append("\r\n \r\n版本名称: " + SDKVersion.getVersionName());
        sb.append("\r\n \r\n构建版本: " + SDKVersion.getBuildName());
        sb.append("\r\n \r\n构建时间: " + SDKVersion.getBuildTime());
        sb.append("\r\n \r\nTAG标签: " + SDKVersion.getBuildTag());
        sb.append("\r\n \r\nHEAD值: " + SDKVersion.getBuildHead());
        infoTv.setText(sb.toString());
    }
}
