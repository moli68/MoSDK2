package com.moli68.library.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.moli68.library.initialization.MoliSDK;

public class ToastUtils {
    private static Toast mToast = null;

    private static Context mContext;

    public static void init(Context context){
        if (mContext == null){
            mContext = context;
            mToast = new Toast(mContext);


        }else {
            Log.e(MoliSDK.TAG,"未初始化lib");
            return;
        }
    }

    /**
     *          short Toast
     * @param msg
     */
    public static void showShortToast(String msg){
        if (mToast == null){
            mToast = Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
        }else {
            mToast = Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
            //mToast.setText(msg);
            //mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     *          long Toast
     * @param msg
     */
    public static void showLongToast(String msg){
        if (mToast == null){
            mToast = Toast.makeText(mContext,msg,Toast.LENGTH_LONG);
        }else {
            mToast = Toast.makeText(mContext,msg,Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}
