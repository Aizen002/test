package com.wh.demo;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Author: wanghao
 * Date: 2023/10/9 16:03
 * Description:
 */
public class Demo004 {


    public static void main(String[] args) throws JSONException, UnsupportedEncodingException {

        String url = "https%3A%2F%2Fiovimage.radio.cn%2Fkradio_live_radio%2FilL3i4eg8rk-1698641130066.aac";
        String decode = URLDecoder.decode(url, "UTF-8");
        System.out.println(decode);


    }
}
