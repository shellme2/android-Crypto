package com.eebbk.bfc.crypto;

import com.eebbk.bfc.crypto.version.Build;

/**
 * @author liuyewu
 * @company EEBBK
 * @function version interface
 * @date 2016/10/22
 */
public class SDKVersion {

    private SDKVersion(){
        // you can't instance me from outside this class
    }

    /**
     * 获取库名称
     * @return 返回库的名称
     */
    public static String getLibraryName(){
        return Build.LIBRARY_NAME;
    }

    /**
     * 构建时的版本值，如：1, 2, 3, ...
     * @return 返回版本值
     */
    public static int getSDKInt(){
        return Build.VERSION.SDK_INT;
    }

    /**
     * 版本名称，如：1.0.0, 2.1.2-alpha, ...
     * @return 返回版本名称
     */
    public static String getVersionName(){
        return Build.VERSION.VERSION_NAME;
    }

    /**
     * 构建版本以及时间，主要从git获取,由GIT_TAG + "_" + GIT_SHA + "_" + BUILD_TIME组成
     * @return 返回构建版本以及时间
     */
    public static String getBuildName(){
        return Build.BUILD_NAME;
    }

    /**
     * 构建时间
     * @return 返回构建时间
     */
    public static String getBuildTime(){
        return Build.BUILD_TIME;
    }

    /**
     * 构建时的git 标签
     * @return 返回标签
     */
    public static String getBuildTag(){
        return Build.GIT_TAG;
    }

    /**
     * 构建时的git HEAD值
     * @return 返回HEAD值
     */
    public static String getBuildHead(){
        return Build.GIT_HEAD;
    }

}
