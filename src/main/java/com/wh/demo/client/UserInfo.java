package com.wh.demo.client;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: wanghao
 * Date: 2023/10/30 10:30
 * Description:
 */
@Data
@Builder
public class UserInfo implements Serializable {

    private String account;

    private String nickName;

    private String avatar;
}
