package com.moli68.library.callback;

import com.moli68.library.beans.MoBaseResult;

/**
 * 基本的请求回调
 * @author zeoy92
 */
public interface CallbackInterface<T extends MoBaseResult> {
    /**
     * 请求成功回调
     * @param result
     */
    public void onSucceed(T result);

    /**
     * 请求失败回调
     * @param e 错误
     * @param msg 其他信息
     */
    public void onFailed(Exception e,String msg);
}
