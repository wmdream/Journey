package com.journey.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/17.
 */
public class JsonUtil {

    private String getJsonValue(JSONObject temp, String key) {
        String res = "";
        try {
            res = temp.getString(key);
        } catch (JSONException e) {
        }
        return res;
    }

    public List<BinaryPositionInfo> getBinaryOrders(String jsonStr){
        /**
         * {"code":0,"msg":[{"user_id":10002,"position_id":1,"position_state":1,"excode":"WGJS","excode_name":"\u8d35\u91d1\u5c5e","code":"XAU","code_name":"\u73b0\u8d27\u9ec4\u91d1",
         * "direction":1,"expiryunixtime":1414137600,"unixtime":0,"investment":25,"price":"1231.37","expiryprice":"1231.22","potentialpayout":"0",
         * "update_time":"2014-10-24 16:04:41","created_time":"0000-00-00 00:00:00"},
         * */
        List<BinaryPositionInfo> list = null;
        try {
            JSONObject object = new JSONObject(jsonStr);
            String s = object.getString("msg");
            //        MyApplication.htlog.i("msg -> "+s);//[{},{},{}]
            JSONArray array = new JSONArray(s);
            list = new ArrayList<BinaryPositionInfo>();
            //     for(int i=0;i<array.length();i++){
            for(int i=array.length()-1;i>=0;i--){
                BinaryPositionInfo info = new BinaryPositionInfo();
                JSONObject obj = array.getJSONObject(i);
//                info.setUser_id(getJsonValue(obj,M2005Const.PARAM_USERID));
//                info.setExcode(getJsonValue(obj,M2005Const.PARAM_EXCODE));
//                info.setExcode_name(getJsonValue(obj,M2005Const.PARAM_EXCODE_NAME));
//                info.setCode(getJsonValue(obj,M2005Const.PARAM_CODE));
//                info.setCode_name(getJsonValue(obj,M2005Const.PARAM_CODE_NAME));
//                info.setOrderprice(getJsonValue(obj,M2005Const.PARAM_PRICE));
//                info.setOrdertime(getJsonValue(obj,M2005Const.PARAM_UNIX_TIME));
//                info.setDirection(Integer.parseInt(getJsonValue(obj,M2005Const.PARAM_DIRECTION)));
//                info.setPosition_id(getJsonValue(obj,M2005Const.PARAM_POSITION_ID));
//                info.setPosition_state(Integer.parseInt(getJsonValue(obj,M2005Const.PARAM_POSITION_STATE)));
//                info.setExpirytime(getJsonValue(obj,M2005Const.PARAM_EXPIRY_UNIXTIME));
//                info.setExpiryprice(getJsonValue(obj,M2005Const.PARAM_EXPIRY_PRICE));
//                info.setInvestment(getJsonValue(obj,M2005Const.PARAM_INVESTMENT));
//                info.setPotentialpayout(getJsonValue(obj,M2005Const.PARAM_POTENTIAL_PAYOUT));
                list.add(info);
            }
            return list;
        }catch (Exception e){
            //MyApplication.htlog.i("Exception -> "+e.toString());
            return null;
        }
    }


    class BinaryPositionInfo {
        /**
         * user_id
         * position_id
         * position_state
         * excode
         * excode_name
         * code
         * code_name
         * direction
         * expiryunixtime
         * unixtime
         * investment
         * price
         * expiryprice
         * potentialpayout
         * created_time
         * update_time
         */
    }
}
