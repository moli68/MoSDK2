/**
 * Project Name:SimonFramework
 * File Name:IntentUtils.java
 * Package Name:com.simon.framework.utils
 * Date:2016-5-19 下午2:22:39
 * Copyright (c) 2016, simon@cmonbaby.com All Rights Reserved.
 */
package com.moli68.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

/**
 * <strong>Intent意图跳转工具类</strong>
 * <ul>
 * <li><strong>activity：当前上下文</strong></li>
 * <li><strong>fragment：fragment跳转</strong></li>
 * <li><strong>targetClass：目标跳转类</strong></li>
 * <li><strong>isForResult：是否请求回调</strong></li>
 * <li><strong>requestCode：请求标识码</strong></li>
 * <li><strong>isResult：是否回调</strong></li>
 * <li><strong>resultCode：回调标识码</strong></li>
 * <li><strong>isFinish：是否销毁</strong></li>
 * <li><strong>获取传递参数Bundle </strong> {@link #getBundle(Context)}</li>
 * <li><strong>获取序列Serializable对象 </strong> {@link #getSerializable(Context, String)}</li>
 * <li><strong>获取序列Parcelable对象 </strong> {@link #getParcelable(Context, String)}</li>
 * </ul>
 *
 * @Title IntentUtils
 * @Package com.simon.framework.utils
 * @Description Intent意图跳转工具类
 * @author simon
 * @date 2016-5-19 下午2:22:39
 * @since JDK1.8 SDK6.0.1
 * @version V2.3.4
 */
public class IntentUtils {

    private Activity activity;
    private Fragment fragment;
    private Class<? extends Activity> targetClass; // 目标跳转类，不能为空
    private boolean isForResult; // 是否请求回调
    private int requestCode; // 请求标识码
    private boolean isResult; // 是否回调
    private int resultCode; // 回调标识码
    private boolean isFinish; // 是否销毁
    private Bundle bundleParams;
    private int RESULTCODE1 = 112;
    private int REQUESTCODE1 = 113;

    public static Builder builder(Object obj) {
        if (obj instanceof Fragment) {
            return new Builder((Fragment) obj);
        } else {
            return new Builder((Activity) obj);
        }
    }

    public IntentUtils(Builder builder) {
        this.activity = builder.activity;
        this.fragment = builder.fragment;
        this.targetClass = builder.targetClass;
        this.isForResult = builder.isForResult;
        this.requestCode = builder.requestCode;
        this.isResult = builder.isResult;
        this.resultCode = builder.resultCode;
        this.isFinish = builder.isFinish;
        this.bundleParams = builder.bundleParams;
        startWork();
    }

    /**
     * 获取传递参数Bundle
     * @param context 参数：当前上下文
     * @return Bundle绑定参数
     */
    public static Bundle getBundle(Context context) {
        return ((Activity) context).getIntent().getExtras();
    }

    /**
     * 获取传递参数序列化实体
     * @param context 参数：当前上下文
     * @return Bundle绑定参数
     */
    public static Serializable getSerializable(Context context, String tag) {
        return getBundle(context).getSerializable(tag);
    }

    /**
     * 获取传递参数序列化实体
     * @param context 参数：当前上下文
     * @return Bundle绑定参数
     */
    public static Serializable getParcelable(Context context, String tag) {
        return getBundle(context).getParcelable(tag);
    }

    private void startWork() {
        if (activity == null) {
            return;
        }
        if (targetClass == null && !isResult) { // 回调时不考虑目标Class
            ToastUtils.showShortToast( "跳转目标Class为空，无法跳转");
            return;
        }

        Intent intent = new Intent();
        if (bundleParams.size() != 0) {
            intent.putExtras(bundleParams); // 有参数传
        }

        if (isForResult) {
            intent.setClass(activity, targetClass);
            checkForResultJump(intent);
            return;
        }

        if (isResult) {
            activity.setResult(resultCode == 0 ? RESULTCODE1 : resultCode, intent);
            activity.finish();
            return;
        }

        intent.setClass(activity, targetClass);
        checkJump(intent);

        if (isFinish) {
            activity.finish();
        }
    }

    private void checkJump(Intent intent) {
        if (fragment != null) {
            fragment.startActivity(intent);
        } else {
            activity.startActivity(intent);
        }
    }

    private void checkForResultJump(Intent intent) {
        if (fragment != null) {
            fragment.startActivityForResult(intent, requestCode == 0 ? REQUESTCODE1 : requestCode);
        } else {
            activity.startActivityForResult(intent, requestCode == 0 ? REQUESTCODE1 : requestCode);
        }
    }

    public static final class Builder {

        private Activity activity;
        private Fragment fragment;
        private Class<? extends Activity> targetClass; // 目标跳转类，不能为空
        private boolean isForResult; // 是否请求回调
        private int requestCode; // 请求标识码
        private boolean isResult; // 是否回调
        private int resultCode; // 回调标识码
        private boolean isFinish; // 是否销毁
        private Bundle bundleParams = new Bundle();

        /** Activity类型跳转 */
        public Builder(Activity activity) {
            this.activity = activity;
        }

        /** Fragment类型跳转 */
        public Builder(Fragment fragment) {
            this.fragment = fragment;
        }

        /** 目标跳转类，不能为空 */
        public Builder targetClass(Class<? extends Activity> targetClass) {
            this.targetClass = targetClass;
            return this;
        }

        /** 是否请求回调 */
        public Builder isForResult(boolean forResult) {
            this.isForResult = forResult;
            return this;
        }

        /** 请求标识码 */
        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        /** 是否回调 */
        public Builder isResult(boolean result) {
            isResult = result;
            return this;
        }

        /** 回调标识码 */
        public Builder resultCode(int resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        /** 是否销毁当前Activity */
        public Builder isFinish(boolean finish) {
            isFinish = finish;
            return this;
        }

        /** 添加int类型参数，可以重复添加 */
        public Builder intParams(String key, int intParams) {
            bundleParams.putInt(key, intParams);
            return this;
        }

        /** 添加long类型参数，可以重复添加 */
        public Builder longParams(String key, long longParams) {
            bundleParams.putLong(key, longParams);
            return this;
        }

        /** 添加String类型参数，可以重复添加 */
        public Builder stringParams(String key, String stringParams) {
            bundleParams.putString(key, stringParams);
            return this;
        }

        /** 添加boolean类型参数，可以重复添加 */
        public Builder booleanParams(String key, boolean booleanParams) {
            bundleParams.putBoolean(key, booleanParams);
            return this;
        }

        /** 添加Serializable类型参数，可以重复添加 */
        public Builder serializableObj(String key, Serializable serializableObj) {
            bundleParams.putSerializable(key, serializableObj);
            return this;
        }

        /** 添加Parcelable类型参数，可以重复添加 */
        public Builder parcelableObj(String key, Parcelable parcelableObj) {
            bundleParams.putParcelable(key, parcelableObj);
            return this;
        }

        public IntentUtils jump() {
            return new IntentUtils(this);
        }
    }
}