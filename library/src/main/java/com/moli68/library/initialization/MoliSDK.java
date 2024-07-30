package com.moli68.library.initialization;

import android.content.Context;


import androidx.annotation.NonNull;

import com.moli68.library.contants.Contants;
import com.moli68.library.oaid_tool.DevicesIDsHelper;
import com.moli68.library.util.CPResourceUtils;
import com.moli68.library.util.MapUtils;
import com.moli68.library.util.SpUtils;
import com.moli68.library.util.ToastUtils;
import com.moli68.library.util.Utils;


public class MoliSDK {
    public static String TAG = "MoliSDK";

    private static Context mContext;

    public static void start(Context context,String commonUrl){
        try {
            if (mContext == null){
                mContext = context;
            }
            SpUtils.getInstance().init(mContext);
            initOaid(context);
            CPResourceUtils.init(mContext);
            ToastUtils.init(mContext);
            MapUtils.init(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean temp;
        if (Utils.isEmpty(commonUrl)){
            temp = false;
        }else {
            temp = true;
            SpUtils.getInstance().putString(Contants.COMMON_URL,commonUrl);
        }
        SpUtils.getInstance().putBoolean(Contants.HAS_DEFINE_COMMON_URL,temp);
    }

    private static void initOaid(Context context) {
        DevicesIDsHelper.AppIdsUpdater updater = new DevicesIDsHelper.AppIdsUpdater() {
            @Override
            public void OnIdsAvalid(@NonNull String ids) {
                String temp = ids;
               SpUtils.getInstance().putString(DevicesIDsHelper.OAID,ids);
            }
        };

        try {
            DevicesIDsHelper iDsHelper = new DevicesIDsHelper(updater);
            iDsHelper.getOAID(context);
        }catch (Exception e){

        }


    }

    public static void start(Context context){
        start(context,null);
    }

}
