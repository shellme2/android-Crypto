package com.eebbk.bfc.demo.crypto.util;

import android.content.Context;
import android.content.Intent;

import com.eebbk.bfc.demo.crypto.basic.BasicFunctionTestActivity;
import com.eebbk.bfc.demo.crypto.basic.asymmetry.AsymmetryTestActivity;
import com.eebbk.bfc.demo.crypto.basic.asymmetry.RsaActivity;
import com.eebbk.bfc.demo.crypto.basic.base64.Base64TestActivity;
import com.eebbk.bfc.demo.crypto.basic.irreversible.IrreversibleTestActivity;
import com.eebbk.bfc.demo.crypto.basic.AlgorithmTestActivity;
import com.eebbk.bfc.demo.crypto.basic.irreversible.Md5Activity;
import com.eebbk.bfc.demo.crypto.basic.irreversible.ShaActivity;
import com.eebbk.bfc.demo.crypto.basic.symmetry.AesActivity;
import com.eebbk.bfc.demo.crypto.basic.symmetry.DesActivity;
import com.eebbk.bfc.demo.crypto.basic.symmetry.DesedeActivity;
import com.eebbk.bfc.demo.crypto.basic.symmetry.SymmetryTestActivity;
import com.eebbk.bfc.demo.crypto.basic.VersionInfoTestActivity;
import com.eebbk.bfc.demo.crypto.limit.LimitActivity;
import com.eebbk.bfc.demo.crypto.other.OtherActivity;
import com.eebbk.bfc.demo.crypto.performance.PerformanceActivity;
import com.eebbk.bfc.demo.crypto.safe.SafeActivity;

/**
 * @author liuyewu
 * @company EEBBK
 * @function Intent Utils
 * @date 2016/11/18
 */
public class IntentUtils {

    private static final Class<?> sBasicFunctionTest= BasicFunctionTestActivity.class;
    private static final Class<?> sSafeTest= SafeActivity.class;
    private static final Class<?> sLimitTest= LimitActivity.class;
    private static final Class<?> sPerformanceTest= PerformanceActivity.class;
    private static final Class<?> sOtherTest= OtherActivity.class;

    private static final Class<?> sAlgorithmTest= AlgorithmTestActivity.class;
    private static final Class<?> sIrreversibleTest = IrreversibleTestActivity.class;
    private static final Class<?> sBase64Test = Base64TestActivity.class;
    private static final Class<?> sSymmetryTest= SymmetryTestActivity.class;
    private static final Class<?> sAsymmetryTest= AsymmetryTestActivity.class;

    private static final Class<?> sVersionInfoTest = VersionInfoTestActivity.class;

    private static final Class<?> sMd5Test= Md5Activity.class;
    private static final Class<?> sShaTest = ShaActivity.class;
    private static final Class<?> sDesTest = DesActivity.class;
    private static final Class<?> sDesedeTest = DesedeActivity.class;
    private static final Class<?> sAesTest= AesActivity.class;
    private static final Class<?> sRsaTest= RsaActivity.class;

    private IntentUtils(){}

    public static void gotoBasicFunctionTest(Context context){
        startActivity(context,sBasicFunctionTest);
    }

    public static void gotoSafeTest(Context context){
        startActivity(context,sSafeTest);
    }

    public static void gotoLimitTest(Context context){
        startActivity(context,sLimitTest);
    }

    public static void gotoPerformanceTest(Context context){
        startActivity(context,sPerformanceTest);
    }

    public static void gotoOtherTest(Context context){
        startActivity(context,sOtherTest);
    }


    public static void gotoAlgorithmTest(Context context){
        startActivity(context,sAlgorithmTest);
    }
    public static void gotoBase64Test(Context context){
        startActivity(context, sBase64Test);
    }
    public static void gotoIrreversibleTest(Context context){
        startActivity(context, sIrreversibleTest);
    }
    public static void gotoSymmetryTest(Context context){
        startActivity(context,sSymmetryTest);
    }
    public static void gotoAsymmetryTest(Context context){
        startActivity(context,sAsymmetryTest);
    }

    public static void gotoVersionInfoTest(Context context){
        startActivity(context, sVersionInfoTest);
    }

    public static void gotoMd5Test(Context context){
        startActivity(context,sMd5Test);
    }
    public static void gotoShaTest(Context context){
        startActivity(context, sShaTest);
    }
    public static void gotoDesTest(Context context){
        startActivity(context, sDesTest);
    }
    public static void gotoDesedeTest(Context context){
        startActivity(context, sDesedeTest);
    }
    public static void gotoAesTest(Context context){
        startActivity(context,sAesTest);
    }
    public static void gotoRsaTest(Context context){
        startActivity(context,sRsaTest);
    }


    public static void startActivity(Context context,Class activityClass){
        Intent intent=new Intent(context,activityClass);
        context.startActivity(intent);
    }
}
