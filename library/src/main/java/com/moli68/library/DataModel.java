package com.moli68.library;


import com.google.gson.Gson;
import com.moli68.library.beans.MoAdsBean;
import com.moli68.library.beans.MoControlBean;
import com.moli68.library.beans.MoDocBean;
import com.moli68.library.beans.MoDocsResultBean;
import com.moli68.library.beans.MoGoodsBean;
import com.moli68.library.beans.MoLoginDataBean;
import com.moli68.library.beans.MoLoginResultBean;
import com.moli68.library.beans.MoMemberBean;
import com.moli68.library.beans.MoUpDataResult;
import com.moli68.library.contants.Contants;
import com.moli68.library.util.GsonUtils;
import com.moli68.library.util.SpUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DataModel {

    private static DataModel instance;

    private static final String APPDATA = "appconfig";
    private static final String APPDOCS = "appdocs";
    private static final String APPLOGINDATA = "applogindata";
    private MoUpDataResult data;
    private MoLoginDataBean loginData;
    private Gson gson;
    private MoDocsResultBean moDocsResultBean;

    private boolean hasReg,hasUpdata,hasGetDoc,hasLogin;


    /**
     *
     */
    private DataModel() {
        gson = new Gson();
        data = gson.fromJson(SpUtils.getInstance().getString(APPDATA), MoUpDataResult.class);
        moDocsResultBean = gson.fromJson(SpUtils.getInstance().getString(APPDOCS),MoDocsResultBean.class);
        loginData = gson.fromJson(SpUtils.getInstance().getString(APPLOGINDATA),MoLoginDataBean.class);
        initBoolean();
    }

    /**
     * 初始化基本参数
     */
    private void initBoolean() {
        hasReg = SpUtils.getInstance().getBoolean(Contants.HAS_REG,false);
        hasUpdata = SpUtils.getInstance().getBoolean(Contants.HAS_UPDATA,false);
        hasGetDoc = SpUtils.getInstance().getBoolean(Contants.HAS_GET_DOCS,false);
        hasLogin = SpUtils.getInstance().getBoolean(Contants.HAS_LOGIN,false);
    }

    public static DataModel getDefault() {
        if (instance == null) {
            synchronized (DataModel.class) {
                if (instance == null) {
                    instance = new DataModel();
                }
            }
        }
        return instance;
    }

    /**
     * @return 获取登录相关的数据
     */
    public MoLoginDataBean getLoginData() {
        if (isHasLogin()){
            return loginData;
        }else {
            return null;
        }

    }

    /**
     * @return 获取登录的手机号
     */
    public String getLoginPhone(){
        if (getLoginData()!=null){
            return getLoginData().getTl();
        }
        return "未绑定手机号";
    }

    /**
     * @param resultBean 保存文档数据
     */
    public void saveDocs(MoDocsResultBean resultBean){
        SpUtils.getInstance().putString(APPDOCS,gson.toJson(resultBean));
        moDocsResultBean = resultBean;
    }

    /**
     * @param jsonstring 判断是否登录成功并保存数据
     */
    public void savaLoginDataString(String jsonstring){
        MoLoginResultBean loginResultBean = GsonUtils.getFromClass(jsonstring,MoLoginResultBean.class);
        if (loginResultBean!=null&&loginResultBean.isIssucc()){
            savaLoginData(loginResultBean.getData());
            DataModel.getDefault().setHasLogin(true);

        }
    }

    /**
     * @param loginData 保存短信登录成功后返回的数据
     */
    private void savaLoginData(MoLoginDataBean loginData) {
        this.loginData = loginData;
        SpUtils.getInstance().putString(APPLOGINDATA,gson.toJson(loginData));
    }

    /**
     * @return 判断是否登录过
     */
    public boolean isHasLogin() {
        return hasLogin;
    }

    /**
     * @param hasLogin 是否登录过，登录失败后将置为false
     */
    public void setHasLogin(boolean hasLogin) {
        this.hasLogin = hasLogin;
        SpUtils.getInstance().putBoolean(Contants.HAS_LOGIN,hasLogin);
    }

    /**
     * @return 是否注册过
     */
    public boolean isHasReg() {
        return hasReg;
    }

    /**
     * 设置是否注册过
     * @param hasReg
     */
    public void setHasReg(boolean hasReg) {
        this.hasReg = hasReg;
        SpUtils.getInstance().putBoolean(Contants.HAS_REG,hasReg);
    }

    /**
     * 判断是否有updata过，
     * @return
     */
    public boolean isHasUpdata() {
        return hasUpdata;
    }

    /**
     * 是否有updata过
     * @param hasUpdata
     */
    public void setHasUpdata(boolean hasUpdata) {
        this.hasUpdata = hasUpdata;
        SpUtils.getInstance().putBoolean(Contants.HAS_UPDATA,hasUpdata);
    }

    /**
     * 是否获取过doc文档
     * @return
     */
    public boolean isHasGetDoc() {
        return hasGetDoc;
    }

    public void setHasGetDoc(boolean hasGetDoc) {
        this.hasGetDoc = hasGetDoc;
        SpUtils.getInstance().putBoolean(Contants.HAS_GET_DOCS,hasGetDoc);
    }

    /**
     * 保存文档字符串
     * @param docsString
     */
    public void saveDocsGsonString(String docsString){
        MoDocsResultBean moDocsResultBean = GsonUtils.getFromClass(docsString,MoDocsResultBean.class);
        if (moDocsResultBean!=null&&moDocsResultBean.isIssucc()){
            saveDocs(moDocsResultBean);
            DataModel.getDefault().setHasGetDoc(true);
        }
    }

    /**
     * 获取所有的文档
     * @return
     */
    public MoDocsResultBean getDocs(){
        return moDocsResultBean;
    }

    /**
     * 获取文档 根据名字
     * @param docName
     * @return
     */
    public MoDocBean getDocByName(String docName){
        if (getDocs()!=null){
            for (MoDocBean docBean:getDocs().getData()){
                if (docName.equals(docBean.getTitle())){
                    return docBean;
                }
            }
        }
        return null;
    }




    /**
     * 保存数据到本地
     *
     * @param updateBean
     */
    public void saveUpdate(MoUpDataResult updateBean) {
        this.data = updateBean;
        SpUtils.getInstance().putString(APPDATA, gson.toJson(updateBean));
    }

    /**
     * 保存同步数据到本地
     * @param string  MoUpDataResult对象的gson字符串
     */
    public void saveUpdataGsonString(String string){
        MoUpDataResult upDataResult = GsonUtils.getFromClass(string,MoUpDataResult.class);
        if (upDataResult!=null&&upDataResult.isIssucc()){
            saveUpdate(upDataResult);
            setHasUpdata(true);
        }
    }

    /**
     * 获取商品详细
     *
     * @return
     */
    public List<MoGoodsBean> getGds() {
        if (data.getMember() == null) {
            data.setService(new ArrayList<>());
        }
        return data.getService();
    }

    /**
     * 获取主页轮播图
     * @return
     */
    public List<MoAdsBean> getMainAds(){
        return  getMoAdsByName("轮播");
    }

    /**
     * 根据名字获取图片数组
     * @return
     */
    public List<MoAdsBean> getMoAdsByName(String name){
        List<MoAdsBean> result = new ArrayList<>();
        if (data!=null&&data.getImgs()!=null){
            for (MoAdsBean adsBean:data.getImgs()){
                if (adsBean.getName().equals(name)){
                    result.add(adsBean);
                }
            }
        }
        return  result;
    }

    /**
     * 通过名字获取单张图片
     * @param name
     * @return
     */
    public MoAdsBean getMoAdByName(String name){
        if (data!=null&&data.getImgs()!=null){
            for (MoAdsBean adsBean:data.getImgs()){
                if (adsBean.getName().equals(name)){
                    return adsBean;
                }
            }
        }
        return  null;
    }

    /**
     * 创建时间是否小于12-26
     * @return
     */
    public boolean isSTime (){
        if (data!=null&&data.getMember()!=null){
            return data.getMember().isIsstime();
        }
        return  false;
    }

    public Date getCtime(){
        if (data!=null&&data.getMember()!=null){
            try {
                return new SimpleDateFormat("yyy-MM-dd hh:mm:ss").parse(getCtimeString());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 获取创建时间字符串
     * @return
     */
    public String getCtimeString(){
        if (data!=null&&data.getMember()!=null){
            return data.getMember().getStime();
        }
        return null;
    }

    /**
     * 获取启动图，需要后台配置启动图名字为“启动图”
     * @return
     */
    public MoAdsBean getSplash(){
        return getMoAdByName("启动图");
    }



    /**
     * 判断是否开启所有的功能
     * @return
     */
    public boolean isOpenAllFun(){
        if (getdata()!=null&&getdata().getNode()!=null){
            for (MoControlBean controlBean:getdata().getNode()){
                if (controlBean.getValue1().equals("S0100065")){
                    return controlBean.getValue2() == 99;
                }
            }
        }
        return false;
    }

    /**
     * 获取所有的开关
     * @return
     */
    public List<MoControlBean> getAllControl(){
        if (getdata()!=null){
            return getdata().getNode();
        }
        return null;
    }

    /**
     * 通过后台代号获取开关
     * @param key 后台代号
     * @return
     */
    public MoControlBean getControlByKey(String key){
        if (getAllControl()==null){
            return null;
        }
        for (MoControlBean moControlBean:getAllControl()){
            if (key.equals(moControlBean.getValue1())){
                return moControlBean;
            }
        }

        return null;
    }
    /**
     * 通过后台开关名称获取开关
     * @param name 后台名称
     * @return
     */
    public MoControlBean getControlByName(String name){
        if (getAllControl()==null){
            return null;
        }
        for (MoControlBean moControlBean:getAllControl()){
            if (name.equals(moControlBean.getKey())){
                return moControlBean;
            }
        }

        return null;
    }


    /**
     * 获取vip
     *
     * @return
     */
    public MoMemberBean getVip() {
        if (data!=null){
           // IsVipOutOffTime();
            return data.getMember();
        }else {
            return null;
        }

    }

    /**
     * 判断vip是否过时
     *
     * @return
     */
    public boolean IsVipOutOffTime() {
        try {
            if (getVip() != null && getVip().getOver()==0) {
                Date now = new Date(System.currentTimeMillis()-86400000);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Date viptiam = sdf.parse(getVip().getEnd_time());

                if (viptiam.before(now)) {
                    data.getMember().setOver(1);
                    saveUpdate(data);
                }
            }
        } catch (ParseException e) {
            if (!data.getMember().getEnd_time().equals("永久")) {
                data.getMember().setOver(1);
                saveUpdate(data);
            }
            e.printStackTrace();
        }
        if (data != null && data.getMember() != null) {
            return data.getMember().getOver()==1;
        } else {
            return true;
        }
    }


    /**
     * @return 获取qq号码
     */
    public String getContrct() {
        if (data != null && data.getQq() != null) {
            return data.getQq();
        } else {
            return "";
        }
    }

    public MoUpDataResult getdata() {
        if (isHasUpdata()){
            return data;
        }else {
            return null;
        }

    }


}
