package com.eebbk.bfc.demo.crypto.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.eebbk.bfc.demo.crypto.performance.BlockCanaryConfig;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author liuyewu
 * Push—demo的application类，用于初始化等操作
 * 2016.09.20
 */
public class MyApplication extends Application {

    private static final String TAG=MyApplication.class.getName();
    private static final boolean DEVELOPER_MODE=true;

    public static boolean isInit=false;
    private static Context sContext;

    @Override
    public void onCreate() {
        sContext = this;
        super.onCreate();
        if (DEVELOPER_MODE) {
            initStrictMode();
            initLeakCanary();
            initBlockCanary();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    private void initBlockCanary() {
        BlockCanary.install(this, new BlockCanaryConfig()).start();
    }

    private void initLeakCanary() {
        // leakcanary默认只监控Activity的内存泄露
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

    }


    private void initStrictMode() {
        //针对线程的监视策略
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        //针对vm
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
    public static Context getAppContext() {
        return sContext;
    }
}
