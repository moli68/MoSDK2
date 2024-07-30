package com.moli68.library.callback;

import java.io.IOException;

import okhttp3.Request;

public interface DataCallBack {

    void requestFailure(Request request, IOException io);//失败

    void requestSuceess(String result) throws Exception;//成功
}
