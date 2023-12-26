package com.wh.demo.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: wanghao
 * Date: 2023/10/27 12:10
 * Description:
 */
@Data
@Builder
public class RankUserVO implements Serializable {

    //用户账号
    private String account;
    //用户昵称
    private String nickName;
    //用户头像
    private String avatar;

    public static void main(String[] args) {

        List<RankUserVO> list = new ArrayList<>();

        list.add(RankUserVO.builder()
                        .account("11111")
                        .nickName("哈哈123")
                        .avatar("12323131312")
                .build());

        list.add(RankUserVO.builder()
                .account("1111122")
                .nickName("哈哈12322")
                .avatar("1232313131222")
                .build());

        Map<String,Object> map = new HashMap<>();
        map.put("rewardRanking",list);
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("account","11111");
        object.put("nickName","哈哈123");
        object.put("avatar","1231231312");
        array.add(object);

        object = new JSONObject();
        object.put("account","11111222");
        object.put("nickName","哈哈12322");
        object.put("avatar","123123131222");
        array.add(object);
        map.put("rewardRanking2",array);

        System.out.println(JSONObject.toJSONString(map));



    }


}
