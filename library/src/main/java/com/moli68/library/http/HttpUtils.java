package com.moli68.library.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.moli68.library.DataModel;
import com.moli68.library.beans.MoBaseResult;
import com.moli68.library.callback.BaseCallback;
import com.moli68.library.callback.DataCallBack;
import com.moli68.library.callback.SimpleCallback;
import com.moli68.library.contants.API;
import com.moli68.library.contants.Contants;
import com.moli68.library.util.CPResourceUtils;
import com.moli68.library.util.GsonUtils;
import com.moli68.library.util.MapUtils;
import com.moli68.library.util.SpUtils;
import com.moli68.library.util.ToastUtils;
import com.moli68.library.util.Utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

    private static HttpUtils mHttpUtils;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;



    public static final int GET_HTTP_TYPE = 1;//get请求
    public static final int POST_HTTP_TYPE = 2;//post请求
    public static final int UPLOAD_HTTP_TYPE = 3;//上传请求
    public static final int DOWNLOAD_HTTP_TYPE = 4;//下载请求

    private Request request = null;

    private MessageDigest alga;

    private Map<String,String> resultMap;

    private String string;

    private boolean isHave;

    private Gson gson;


    private String commonUrl;

    private HttpUtils(){
        try {
            mOkHttpClient = new OkHttpClient();
            mOkHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS);
            mHandler = new Handler(Looper.getMainLooper());
            gson = new Gson();
            alga = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        initCommonUrl();
    }

    /**
     * 初始化自定义链接
     */
    private void initCommonUrl() {
        if (SpUtils.getInstance().getBoolean(Contants.HAS_DEFINE_COMMON_URL,false)){
            commonUrl = SpUtils.getInstance().getString(Contants.COMMON_URL,API.COMMON_URL);
        }else {
            commonUrl = API.COMMON_URL;
        }
    }

    public static HttpUtils getInstance(){
        if (mHttpUtils == null){
            synchronized (HttpUtils.class){
                if (mHttpUtils == null){
                    mHttpUtils = new HttpUtils();
                }
            }
        }
        return mHttpUtils;
    }

    /**
     *          提供对外调用的请求接口
     * @param callBack      回调接口
     * @param url           路径
     * @param type          请求类型
     * @param paramKey      请求参数
     * @param paramValue    请求值
     */
    public static void httpsNetWorkRequest(final DataCallBack callBack, final String url, final int type, final String[] paramKey, final Object[] paramValue){
            getInstance().inner_httpsNetWorkRequest(callBack,url,type,paramKey,paramValue);
    }

    /**
     *          内部处理请求的方法
     * @param callBack      回调接口
     * @param url           路径
     * @param type          请求类型
     * @param paramKey      请求参数
     * @param paramValue    请求值
     */
    private void inner_httpsNetWorkRequest(final DataCallBack callBack,final String url,final int type,final String[] paramKey,final Object[] paramValue){
        RequestBody requestBody = null;
        FormBody.Builder builder = new FormBody.Builder();

        Map<String,String> map = new TreeMap<String,String>();

        map.put("app_key", CPResourceUtils.getString("appid"));
        map.put("app_sign",null);
        map.put("device_number",CPResourceUtils.getDevice());

        if (paramKey != null){
            for (int i = 0; i < paramKey.length; i++) {
                map.put(paramKey[i],String.valueOf(paramValue[i]));
            }
            resultMap = sortMapByKey(map);
        }

        String str="";
        int num = 0;

        boolean isFirst = true;
        switch (type){
            case GET_HTTP_TYPE:
                request = new Request.Builder().url(commonUrl+url).build();
                break;
            case POST_HTTP_TYPE:
                /**
                 * 循环遍历获取key值，拼接sign字符串
                 */
                for (Map.Entry<String, String> entry :
                        resultMap.entrySet()) {
                    if (entry.getValue() == null){
                        continue;
                    }
                    num++;
                    if (isFirst){
                        str += entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(),Base64.DEFAULT).trim();
                        isFirst = !isFirst;
                    }else {
                        str = str.trim();
                        str += "&" + entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(),Base64.DEFAULT).trim();
                        if (num == resultMap.size() - 1){
                            str += "&" + "key" + "=" + CPResourceUtils.getString("appkey");
                        }
                    }
                }

                //去除换行
                    str = str.replace("\n","");
                //去除空格
                    str = str.replace("\\s","");
                    isFirst = !isFirst;
                    alga.update(str.getBytes());

                /**
                 * 循环遍历value值，添加到表单
                 **/
                for (Map.Entry<String, String> entry :
                        resultMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (value == null) {
                        value = null;
                    }
                    if (key.equals("app_sign")) {
                        value = Utils.byte2hex(alga.digest());
                    }else if (key.equals("key")){
                        continue;
                    }
                    builder.add(key,value);
                }

                    requestBody = builder.build();
                request = new Request.Builder().url(commonUrl+ url).post(requestBody).build();
                break;
            case UPLOAD_HTTP_TYPE:
                MultipartBody.Builder multipartBody = new MultipartBody.Builder("-----").setType(MultipartBody.FORM);
                if (paramKey != null && paramValue != null){
                    for (int i = 0; i < paramKey.length; i++) {
                        multipartBody.addFormDataPart(paramKey[i],String.valueOf(paramValue[i]));
                    }
                    requestBody = multipartBody.build();
                }
                request = new Request.Builder().url(commonUrl+url).post(requestBody).build();
                break;
                default:
                    break;
        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request,e,callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request,e,callBack);
                }
                deliverDataSuccess(result,callBack);
            }
        });
    }

    /**
     *          分发失败的时候回调
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e,final DataCallBack callBack){
        mHandler.post(()->{
           if (callBack != null){
               callBack.requestFailure(request,e);
           }
        });
    }

    /**
     *          分发成功的时候回调
     * @param result
     * @param callBack
     */
    private void deliverDataSuccess(final String result,final DataCallBack callBack){
        mHandler.post(()->{
            if (callBack != null){
                try {
                    callBack.requestSuceess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *          map根据key值比较大小
     * @param map
     * @return
     */
    private static Map<String,String> sortMapByKey(Map<String,String> map){
        if (map == null || map.isEmpty()){
            return null;
        }

        Map<String,String> sortMap = new TreeMap<String,String>((str1,str2)-> str1.compareTo(str2));
        sortMap.putAll(map);
        return sortMap;
    }


    /**
     *      内部处理Map集合
     *      得到from表单 (post请求)
     * @return
     */
    private RequestBody getRequestBody(Map<String,String> map){
        RequestBody requestBody = null;
        FormBody.Builder builder = new FormBody.Builder();
        resultMap = sortMapByKey(map);

        Log.d("MoliSDK:请求参数：","map:"+resultMap.toString());

        String str="";
        int num = 0;

        boolean isFirst = true;

        /**
         * 循环遍历获取key值，拼接sign字符串
         */
        for (Map.Entry<String, String> entry :
                resultMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            num++;
            if (isFirst) {
                str += entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(), Base64.DEFAULT).trim();
                isFirst = !isFirst;
            } else {
                str = str.trim();
                str += "&" + entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(), Base64.DEFAULT).trim();
                if (num == resultMap.size() - 1) {
                    str += "&" + "key" + "=" + CPResourceUtils.getString("appkey");
                }
            }
        }


        //去除换行
        str = str.replace("\n","");
        //去除空格
        str = str.replace("\\s","");

        Log.d("MoliSDK:请求参数：","string:"+str);
        isFirst = !isFirst;
        alga.update(str.getBytes());


        /**
         * 循环遍历value值，添加到表单
         */
        for (Map.Entry<String, String> entry :
                resultMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                //value报空
                value = "";
            }
            if (key.equals("app_sign")) {
                value = Utils.byte2hex(alga.digest());
            }else if (key.equals("key")){
                continue;
            }
            builder.add(key,value);
        }

        requestBody = builder.build();
        return requestBody;
    }

    /**---------------------------------------------------------------------------分割线-------------------------------------------------------------------------*/



    /**
     *      提供给外部调用的注册接口
     * @param callback      回调函数
     */
    public void postRegister(BaseCallback callback){
        post(commonUrl+API.REGIST_DEVICE, MapUtils.getRegistMap(),callback,API.REGIST_DEVICE);
    }

    /**
     *      提供给外部调用的更新数据接口
     * @param callback      回调函数
     */
    public void postUpdate(BaseCallback callback){
        post(commonUrl+API.UPDATE,MapUtils.getCurrencyMap(),callback,API.UPDATE);
    }




    /**
     *      提供给外部调用的版本更新接口
     * @param callback      回调函数
     */
    public void postNews(BaseCallback callback){
        post(commonUrl+API.GETNEW,MapUtils.getNewMap(),callback);
    }


    /**
     *      提供给外部调用的意见反馈接口
     * @param content       意见内容
     * @param phone         联系方式
     * @param callback      回调函数
     */
    public void postMsgBug(String content,String phone,String photos,BaseCallback callback){
        post(commonUrl+API.FEEDBACK,MapUtils.getFeedBack(content,phone,photos),callback);
    }

    /**
     * 获取文档接口
     * @param callback
     */
    public void postGetDocs(BaseCallback callback){
        post(commonUrl+API.GET_DOC,MapUtils.getFeedBackMap(),callback,API.GET_DOC);
    }


    public void postGetMsgBug(BaseCallback callback){
        post(commonUrl+API.GETFEEDBACK,MapUtils.getFeedBackMap(),callback);
    }


    /**
     * @param tel 电话号码
     * @param smssign   阿里云短信签名
     * @param smscode   阿里云短信模板
     * @param callback  回调
     */
    public void postSendSms(String tel,String smssign,String smscode,BaseCallback callback){
        post(commonUrl+API.SEND_SMS,MapUtils.getSendSMSMap(tel,smscode,smssign),callback);
    }

    /**
     * @param tel 电话号码
     * @param code  验证码
     * @param key   验证码的key
     * @param callback  回调
     */
    public void postLogin(String tel,String code,String key,BaseCallback callback){
        post(commonUrl+API.SMS_LOGIN,MapUtils.getSmsLoginMap(tel,code,key),callback,API.SMS_LOGIN);
    }

    /**
     * @param open_id 微信id
     * @param nick_name  昵称
     * @param headurl   头像url
     * @param callback  回调
     */
    public void postWcLogin(String open_id,String nick_name,String headurl,BaseCallback callback){
        post(commonUrl+API.WC_LOGIN,MapUtils.getWcLoginMap(open_id,nick_name,headurl),callback,API.WC_LOGIN);
    }

    /**
     * @param order_type   订单类型
     * @param order_serivce_id 商品ID
     * @param money         支付金额
     * @param order_way    支付方式
     * @param callback
     */
    public void postOrder(int order_type,int order_serivce_id,float money,int order_way,BaseCallback callback){
        post(commonUrl+API.ORDER_ONE,MapUtils.getOrder(order_type,order_serivce_id,money,order_way),callback);
    }




    public void post(String url, Map<String,String> params, final BaseCallback callback){
        post(url,params,callback,"defaultRequest");
    }

    /**
     *      内部提供的post请求方法
     * @param url           请求路径
     * @param params        请求参数(表单)
     * @param callback      回调函数
     */
    public void post(String url, Map<String,String> params,
                     final BaseCallback callback,String requestType){
        //请求之前调用(例如加载动画)
        callback.onRequestBefore();
          mOkHttpClient.newCall(getRequest(url,params)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //返回失败
                callbackFailure(call.request(),callback,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //返回成功回调
                    String result = response.body().string();

                    //数据更新时先保存到本地再回调
                    if (API.UPDATE.equals(requestType)){
                        DataModel.getDefault().saveUpdataGsonString(result);
                    }

                    if (API.REGIST_DEVICE.equals(requestType)){
                        MoBaseResult result1 = GsonUtils.getFromClass(result,MoBaseResult.class);
                        if (result1!= null&&result1.isIssucc()){
                            DataModel.getDefault().setHasReg(true);
                        }
                    }


                    if (API.GET_DOC.equals(requestType)){
                       DataModel.getDefault().saveDocsGsonString(result);
                    }
                    if (API.SMS_LOGIN.equals(requestType)||API.WC_LOGIN.equals(requestType)){
                        DataModel.getDefault().savaLoginDataString(result);
                    }

                    Log.d("MoliSDK:"+url+":回调数据",result);
                    if (callback.mType == String.class){
                        //如果我们需要返回String类型
                        callbackSuccess(response,result,callback);
                    }else {
                        //如果返回是其他类型,则用Gson去解析
                        try {
                            Object o = gson.fromJson(result, callback.mType);
                            if (!API.SMS_LOGIN.equals(requestType)&&!API.WC_LOGIN.equals(requestType)){
                                callbackSuccess(response,o,callback);
                            }else {
                                postUpdate(new SimpleCallback() {
                                    @Override
                                    public void onSucceed(MoBaseResult result) {
                                        callbackSuccess(response,o,callback);
                                    }
                                    @Override
                                    public void onFailed(Exception e, String msg) {
                                        callbackSuccess(response,o,callback);
                                        ToastUtils.showShortToast("登录成功，数据更新失败，请重新打开软件");
                                    }
                                });
                            }

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            callbackError(response,callback,e);
                        }
                    }
                }else {
                    callbackError(response,callback,null);
                }

            }
        });

    }

    /**
     *      得到Request
     * @param url           请求路径
     * @param params        from表单
     * @return
     */
    private Request getRequest(String url,Map<String,String> params){
         //可以从这么划分get和post请求，暂时只支持post
         //Log.e("请求参数：","url:"+url);
         return new Request.Builder().url(url).post(getRequestBody(params)).build();
    }

    /**
     *      在主线程中执行成功回调
     * @param response      请求响应
     * @param o             类型
     * @param callback      回调函数
     */
    private void callbackSuccess(final Response response, final Object o, final BaseCallback<Object> callback){
        mHandler.post(()->callback.onSuccess(response,o));
    }

    /**
     *      在主线程中执行错误回调
     * @param response      请求响应
     * @param callback      回调函数
     * @param e             响应错误异常
     */
    private void callbackError(final Response response,final BaseCallback callback,Exception e){
        mHandler.post(()->callback.onError(response,response.code(),e));
    }

    /**
     *      在主线程中执行失败回调
     * @param request       请求链接
     * @param callback      回调韩素和
     * @param e             响应错误异常
     */
    private void callbackFailure(final Request request,final BaseCallback callback,final Exception e){
        mHandler.post(()->callback.onFailure(request,e));
    }


    /**
     * 获取当前定义的域名
     * @return commonUrl
     */
    public static String getCommonUrl(){
        if (SpUtils.getInstance().getBoolean(Contants.HAS_DEFINE_COMMON_URL,false)){
            return SpUtils.getInstance().getString(Contants.COMMON_URL,API.COMMON_URL);
        }else {
            return API.COMMON_URL;
        }
    }





}
