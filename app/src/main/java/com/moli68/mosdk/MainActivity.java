package com.moli68.mosdk;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.PayTask;
import com.moli68.library.DataModel;
import com.moli68.library.beans.MoBaseResult;
import com.moli68.library.beans.MoBugsResultBean;
import com.moli68.library.beans.MoDocsResultBean;
import com.moli68.library.beans.MoLoginResultBean;
import com.moli68.library.beans.MoNewsBean;
import com.moli68.library.beans.MoOrderResultBean;
import com.moli68.library.beans.MoUpDataResult;
import com.moli68.library.beans.PayResult;
import com.moli68.library.callback.SimpleCallback;
import com.moli68.library.http.HttpUtils;
import com.moli68.library.util.PermissionUtils;
import com.moli68.library.util.ToastUtils;
import com.moli68.library.util.Utils;

import java.util.Map;



public class MainActivity extends AppCompatActivity {
    private EditText etInput;

    private static final int SDK_PAY_FLAG = 1;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.et_input);

        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case SDK_PAY_FLAG:
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        switch (payResult.getResultStatus()) {
                            case "9000":
                                ToastUtils.showShortToast("支付成功！");
                                break;
                            case "8000":
                                ToastUtils.showShortToast("正在处理中");
                                break;
                            case "4000":
                                ToastUtils.showShortToast("订单支付失败");

                                break;
                            case "5000":
                                ToastUtils.showShortToast("重复请求");
                                break;
                            case "6001":
                                ToastUtils.showShortToast("已取消支付");
                                break;
                            case "6002":
                                ToastUtils.showShortToast("网络连接出错");
                                break;
                            case "6004":
                                ToastUtils.showShortToast("正在处理中");
                                break;
                            default:
                                ToastUtils.showShortToast("支付失败");
                                break;
                        }
                        break;
                    default:
                        break;
                }

            }
        };
    }

    /**
     * 注册设备
     *
     * @param view
     */
    public void reg(View view) {
        HttpUtils.getInstance().postRegister(new SimpleCallback<MoBaseResult>() {
            @Override
            public void onSucceed(MoBaseResult result) {
                ToastUtils.showShortToast(result.toString());
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    /**
     * 更新数据
     *
     * @param view
     */
    public void updata(View view) {
        HttpUtils.getInstance().postUpdate(new SimpleCallback<MoUpDataResult>() {
            @Override
            public void onSucceed(MoUpDataResult result) {
                //ToastUtils.showShortToast(result.toString());

                if (DataModel.getDefault().isSTime()){
                    ToastUtils.showShortToast(DataModel.getDefault().getCtimeString());
                }
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    /**
     * 版本更新
     *
     * @param view
     */
    public void getNew(View view) {
        HttpUtils.getInstance().postNews(new SimpleCallback<MoNewsBean>() {
            @Override
            public void onSucceed(MoNewsBean result) {
                ToastUtils.showShortToast(result.toString());
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    /**
     * 获取文档
     *
     * @param view
     */
    public void getDoc(View view) {
        HttpUtils.getInstance().postGetDocs(new SimpleCallback<MoDocsResultBean>(){
            @Override
            public void onSucceed(MoDocsResultBean result) {
                ToastUtils.showShortToast(result.toString());
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }


    /**
     * 添加反馈内容
     *
     * @param view
     */
    public void addReback(View view) {
        String temp = etInput.getText().toString();
        HttpUtils.getInstance()
                .postMsgBug(Utils.isEmpty(temp) ? "reback_test" : temp,
                        "123456", "",
                        new SimpleCallback() {
                            @Override
                            public void onSucceed(MoBaseResult result) {
                                ToastUtils.showShortToast(result.toString());
                            }
                            @Override
                            public void onFailed(Exception e, String msg) {

                            }
                        });
    }

    /**
     * 获取反馈详情
     *
     * @param view
     */
    public void getFeedback(View view) {
        HttpUtils.getInstance().postGetMsgBug(new SimpleCallback<MoBugsResultBean>() {

            @Override
            public void onSucceed(MoBugsResultBean result) {
                ToastUtils.showShortToast(result.toString());
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 112) {
            PermissionUtils.onRequestPermissionResult(this, Manifest.permission.READ_PHONE_STATE, grantResults, new PermissionUtils.PermissionCheckCallBack() {
                @Override
                public void onHasPermission() {
                    ToastUtils.showShortToast("获取权限成功");
                }

                @Override
                public void onUserHasAlreadyTurnedDown(String... permission) {
                    ToastUtils.showShortToast("拒绝了权限");
                }

                @Override
                public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                    ToastUtils.showShortToast("永久拒绝了权限");
                }
            });
        }
    }

    /**
     * 下单
     *
     * @param view
     */
    public void order(View view) {
        String temp = etInput.getText().toString();
        if (Utils.isEmpty(temp)) {
            ToastUtils.showShortToast("请输入要支付的商品id");
            return;
        }
        int pid;
        try {
            pid = Integer.valueOf(temp);
        } catch (Exception e) {
            ToastUtils.showShortToast("请输入合法的商品id");
            return;
        }

        HttpUtils.getInstance().postOrder(1, pid, 0f, 2,
                new SimpleCallback<MoOrderResultBean>() {

            @Override
            public void onSucceed(MoOrderResultBean result) {
                ToastUtils.showShortToast(result.toString());
                ToastUtils.showShortToast("order成功，正在拉起支付宝");
                Runnable runnable = () -> {
                    PayTask alipay = new PayTask(MainActivity.this);
                    Map<String, String> map = alipay.payV2(result.getPackageX(), true);
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = map;
                    mHandler.sendMessage(msg);
                };

                Thread payThread = new Thread(runnable);
                payThread.start();
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    /**
     * 发送短信
     */

    private String msg,code,tel;

    public void sendsms(View view){
        String temp = etInput.getText().toString();
        if (Utils.isEmpty(temp)){
            ToastUtils.showShortToast("请输入手机号码");
            return;
        }

        HttpUtils.getInstance().postSendSms(temp,"魔力娱乐","SMS_182536222", new SimpleCallback() {

            @Override
            public void onSucceed(MoBaseResult result) {
                ToastUtils.showShortToast("验证码发送成功");
                msg = result.getMsg();
                code = result.getCode();
                tel = temp;
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    /**
     * 登录
     */
    public void login(View view){
        String temp = etInput.getText().toString();
        if (Utils.isEmpty(temp)){
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        HttpUtils.getInstance().postLogin(tel, temp, msg, new SimpleCallback<MoLoginResultBean>() {

            @Override
            public void onSucceed(MoLoginResultBean result) {
                ToastUtils.showShortToast("登录成功");
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });
    }

    /**
     * 获取设备注册权限
     *
     * @param view
     */
    public void getPermission(View view) {
        PermissionUtils.checkAndRequestPermission(this, Manifest.permission.READ_PHONE_STATE, 112);
    }


    public void imageChose(View view) {

        Log.d("moli","是否注册:"+ DataModel.getDefault().isHasReg());
        Log.d("moli","是否updata:"+ DataModel.getDefault().isHasUpdata());
        Log.d("moli","是否getDoc:"+ DataModel.getDefault().isHasGetDoc());
        if (DataModel.getDefault().isHasUpdata()) {
            Log.d("moli","updata:"+ DataModel.getDefault().getdata().toString());
        }


        if (DataModel.getDefault().isHasGetDoc()) {
            Log.d("moli","docs:"+ DataModel.getDefault().getDocs().toString());
        }
    }


    /**
     * 支付宝购买
     *
     * @param pid
     */
    public void pay(int pid) {

        if (pid == -1) {
            return;
        }
        if (Utils.isNetworkAvailable(this)) {
            HttpUtils.getInstance().postOrder(1, pid, 0, 2, new SimpleCallback<MoOrderResultBean>() {


                @Override
                public void onSucceed(MoOrderResultBean response) {
                        if (response != null) {
                            if (!response.isIssucc()) {
                                ToastUtils.showShortToast(response.getMsg());
                                return;
                            }
                            Runnable runnable = () -> {
                                PayTask alipay = new PayTask(MainActivity.this);
                                Map<String, String> map = alipay.payV2(response.getPackageX(), true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = map;
                                mHandler.sendMessage(msg);
                            };

                            Thread payThread = new Thread(runnable);
                            payThread.start();
                        }
                }

                @Override
                public void onFailed(Exception e, String msg) {
                    ToastUtils.showShortToast("链接超时");
                }
            });}



    }

    public void wc_login(View view ) {
        String temp = etInput.getText().toString();
        if (Utils.isEmpty(temp)){
            ToastUtils.showShortToast("请输入微信open_id");
            return;
        }
        HttpUtils.getInstance().postWcLogin(tel, "test_name", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201805%2F03%2F20180503221445_BTUzC.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1666855056&t=da9a2205c481da84b6b25f9098ee6f00", new SimpleCallback<MoLoginResultBean>() {

            @Override
            public void onSucceed(MoLoginResultBean result) {
                ToastUtils.showShortToast("登录成功");
            }

            @Override
            public void onFailed(Exception e, String msg) {

            }
        });

    }
}
