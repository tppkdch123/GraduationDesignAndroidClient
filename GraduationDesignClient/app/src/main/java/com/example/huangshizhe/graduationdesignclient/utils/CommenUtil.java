package com.example.huangshizhe.graduationdesignclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.huangshizhe.graduationdesignclient.enums.RequestEnum;
import com.example.huangshizhe.graduationdesignclient.vo.RoomExtend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangshizhe on 2018/4/18.
 */

public class CommenUtil {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void saveEmail(Context context, String email){
        //获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        //设置参数
        if(email!=null){
            editor.putString("email",email);
        }
        //提交
        editor.commit();
    }

    public static void setCookie(String token){
        SharedPreferences sharedPre=MyApplication.getContext().getSharedPreferences("config", MyApplication.getContext().MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();

        if(token!=null){
            editor.putString("huangshizhetianxiadiyi",token);
        }
        editor.commit();
    }

    public static String getCookie(){
        SharedPreferences sharedPre=MyApplication.getContext().getSharedPreferences("config", MyApplication.getContext().MODE_PRIVATE);
        return sharedPre.getString("huangshizhetianxiadiyi","");
    }

    public static String getEmail(Context context){
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPre.getString("email","");
    }

    public static void savePassword(Context context, String password){
        //获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        //设置参数
        if(password!=null){
            editor.putString("password",password);
        }
        //提交
        editor.commit();
    }

    public static String getPassword(Context context){
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPre.getString("password","");
    }

    public static JsonNode toJsonNode(String json) throws IOException {

        if(StringUtils.isBlank(json)){
            return null;
        }
        return OBJECT_MAPPER.readTree(json);
    }

    public static Integer getStatus(JsonNode jsonNode) throws IOException{
        if(jsonNode==null){
            return null;
        }
        return jsonNode.get("status").asInt();
    }
    public static <T> T toBean(String jsonStr, Class<T> targetClass) throws IOException {
        return OBJECT_MAPPER.readValue(jsonStr, targetClass);
    }

    public static String toJsonStr(Object obj){
        if (obj == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode getPictureByRoomId(Long roomId){
        Map<String,String> params=new HashMap<>();
        params.put("id",roomId.toString());
        String result= HttpConnectionUtil.getJsonResult(RequestEnum.ROOM_EXTEND,params);
        try {
            JsonNode jsonNode= CommenUtil.toJsonNode(result);
            if(jsonNode==null){
                return null;
            }
            if(jsonNode.get("status").asInt()==0){
              return jsonNode.get("data");
            }
            else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<RoomExtend> getAllExtend(JsonNode jsonNode) {
        List<RoomExtend> roomExtends=new ArrayList<>();
        try {
            List list=CommenUtil.toBean(jsonNode.toString(),List.class);
            for(Object object:list){
                RoomExtend roomExtend=CommenUtil.toBean(CommenUtil.toJsonStr(object),RoomExtend.class);
                roomExtends.add(roomExtend);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
return roomExtends;
    }
    
}
