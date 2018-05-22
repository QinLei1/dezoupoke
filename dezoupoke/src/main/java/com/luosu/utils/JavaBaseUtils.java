package com.luosu.utils;

import com.alibaba.fastjson.JSON;
import com.luosu.entity.Uploadgold;

/**
 * Created by User on 2017/3/6.
 */

public class JavaBaseUtils {


    public static String putGold(int userid,int goldnum1,int goldnum2,int goldnum3) {
        Uploadgold gold= new Uploadgold();
        gold.setPainum(77);
        gold.setUser1gold(goldnum1);
        gold.setUser2gold(goldnum2);
        gold.setUser3gold(goldnum3);
        gold.setUserid(userid);
       return JSON.toJSONString(gold);




    }
}
